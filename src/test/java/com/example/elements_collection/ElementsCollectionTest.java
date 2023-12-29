package com.example.elements_collection;

import com.example.PostgresTest;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import org.checkerframework.checker.units.qual.C;
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

@SpringBootTest(classes = ElementsCollectionTest.class)
@EnableAutoConfiguration
@ContextConfiguration
public class ElementsCollectionTest extends PostgresTest {

    @Autowired
    CompanyRepo companyRepo;

    @Test
    public void elementsCollection () {
        Company company = new Company(1L, "c1");

        CompanyDetail detail1 = new CompanyDetail();
        detail1.setDetail("detail 1");
        detail1.setService("module:service");

        CompanyDetail detail2 = new CompanyDetail();
        detail2.setDetail("detail 2");
        detail2.setService("module:service2");

        company.getCompanyDetails().add(detail1);
        company.getCompanyDetails().add(detail2);

        companyRepo.save(company);
    }
}
