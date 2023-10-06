package com.example.onetomany;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OneToManyAppTests {
    @Autowired
    private GroupRepoOneToMany groupRepoOneToMany;
    @Autowired
    private SessionFactory sessionFactory;
    static final int port = 5432;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    )
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(port)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(port), new ExposedPort(port)))
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
    void contextLoads() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setName("Proxym");

        Company company = new Company();
        company.setId(1L);
        company.setName("C1");
        group.getCompanies().add(company);
        groupRepoOneToMany.save(group);

        Group byId = groupRepoOneToMany.findById(1L).get();

        assertThat(byId.getCompanies()).size().isEqualTo(1);
    }

}
