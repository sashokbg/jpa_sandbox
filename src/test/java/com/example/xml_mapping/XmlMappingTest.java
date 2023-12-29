package com.example.xml_mapping;

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
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = { XmlMappingTest.class, XmlConfig.class})
@EnableAutoConfiguration
@ContextConfiguration
class XmlMappingTest extends PostgresTest {
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    GroupRepo groupRepo;

    @Test
    void test() {
        Company company = new Company(1L, "Company");
        Group group = new Group(1L, List.of(company));

        groupRepo.save(group);

        Optional<Group> byId = groupRepo.findById(1L);

        assertThat(byId).isPresent();
    }
}
