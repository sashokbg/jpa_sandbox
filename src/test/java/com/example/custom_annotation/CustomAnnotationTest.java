package com.example.custom_annotation;

import com.example.PostgresTest;
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

@SpringBootTest(classes = CustomAnnotationTest.class)
@EnableAutoConfiguration
@ContextConfiguration
public class CustomAnnotationTest extends PostgresTest {
    @Autowired
    CompanyRepo companyRepo;

    @Test
    void test() {
       Company company = new Company();
       company.setName("Name");
       company.setId(new CompanyPk(1L));
//        companyRepo.save(company);

       company.details.add(new CompanyDetail(company, "1", "2"));

       companyRepo.save(company);
    }
}
