package com.example.weak_entities_one_way;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.Assertions;
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

@SpringBootTest(classes = WeakReposTestOneWay.class)
@EnableAutoConfiguration
@ContextConfiguration
class WeakReposTestOneWay {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    CompanyDetailsRepo detailsOneWayRepo;

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
    void weekEntity() {
        Company company1 = new Company(1L, "C1");
        companyRepo.save(company1);

        CompanyDetail companyDetailWithEntity = new CompanyDetail();
        companyDetailWithEntity.setDetail("DDD");

        Company company = new Company();
        company.setId(new CompanyPk(1L));
        CompanyDetailPk key = new CompanyDetailPk();

        key.service = "service:toto";
        key.companyId = company.getId();
        companyDetailWithEntity.setKey(key);

        detailsOneWayRepo.save(companyDetailWithEntity);

        Assertions.assertThatThrownBy(() -> {
            companyRepo.deleteById(new CompanyPk(1L)); // this will fail due to FK still there
        }).hasMessageContaining("violates foreign key");
    }
}
