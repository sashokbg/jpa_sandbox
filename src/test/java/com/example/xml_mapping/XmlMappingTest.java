package com.example.xml_mapping;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = XmlMappingTest.class)
@EnableAutoConfiguration
@ContextConfiguration
class XmlMappingTest {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    GroupRepo groupRepo;

    static final int port = 5432;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    )
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(port)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(
                            new PortBinding(Ports.Binding.bindPort(port), new ExposedPort(port)))
            ))
            .withReuse(true);

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    public void setHibernateFactoy(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Test
    void test() {
        Company company = new Company(1L, "Company");
        Group group = new Group(1L, List.of(company));

        groupRepo.save(group);

        Optional<Group> byId = groupRepo.findById(1L);

        assertThat(byId).isPresent();
    }
}
