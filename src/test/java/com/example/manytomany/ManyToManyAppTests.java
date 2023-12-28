package com.example.manytomany;

import com.example.encapsulation.EncapsulationTest;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
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

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ManyToManyAppTests.class)
@EnableAutoConfiguration
@ContextConfiguration
class ManyToManyAppTests {
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private CompanyRepo companyRepo;
    private SessionFactory sessionFactory;

    static Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(
            new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)));

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    )
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(5432)
            .withCreateContainerCmdModifier(cmd)
            .withReuse(true);
//            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
//                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
//            ));

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
        group.setName("gr 1");

        groupRepo.save(group);

        Company company = new Company();
        company.setId(9L);
        company.setName("co 1");

        ArrayList<Group> groups = new ArrayList<>();
        groups.add(group);
        company.setGroups(groups);

        companyRepo.save(company);

        Company actual = companyRepo.findById(9L).get();
        assertThat(actual.getGroups().get(0).getName()).isEqualTo("gr 1");
    }

}
