package com.example.encapsulation;


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

@SpringBootTest(classes = EncapsulationTest.class)
@EnableAutoConfiguration
@ContextConfiguration
public class EncapsulationTest  extends PostgresTest {
    @Autowired
    CompanyRepo companyRepo;

    @Test
    public void test() {
        Company company = new Company(new CompanyId("1"), "NAME");
        companyRepo.save(company);

        companyRepo.findAll();
    }

}
