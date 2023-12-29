package com.example.weak_entities_one_way;

import com.example.PostgresTest;
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
class WeakReposTestOneWay extends PostgresTest {
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    CompanyDetailsRepo detailsOneWayRepo;

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
        key.company = company;
        companyDetailWithEntity.setKey(key);

        detailsOneWayRepo.save(companyDetailWithEntity);

        Assertions.assertThatThrownBy(() -> {
            companyRepo.deleteById(new CompanyPk(1L)); // this will fail due to FK still there
        }).hasMessageContaining("violates foreign key");
    }
}
