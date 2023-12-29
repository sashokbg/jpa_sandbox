package com.example.onetomany;

import com.example.PostgresTest;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OneToManyAppTests extends PostgresTest {
    @Autowired
    private GroupRepoOneToMany groupRepoOneToMany;

    @Test
    void contextLoads() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setName("Proxym");

        Company company = new Company();
        company.setId(1L);
        company.setName("C1");
        Rule rule1 = new Rule("rule 1", List.of(new RulePolicy(50, 100)));
        Rule rule2 = new Rule("rule 2", List.of(new RulePolicy(0, 50)));

        company.companyDetails.add(new CompanyDetails(company, "detail 1"));

        company.rules.add(rule1);
        company.rules.add(rule2);

        group.getCompanies().add(company);
        groupRepoOneToMany.save(group);

        company.companyDetails.add(new CompanyDetails(company, "detail 2"));

        groupRepoOneToMany.save(group);

        Group groupResult = groupRepoOneToMany.findById(1L).get();

        assertThat(groupResult.getCompanies()).size().isEqualTo(1);
    }

}
