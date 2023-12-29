package com.example.manytomany_one_way;

import com.example.PostgresTest;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ManyToManyOnewayAppTests extends PostgresTest {
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private CompanyRepo companyRepo;

    @Test
    void contextLoads() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setName("Proxym");

        Company company = new Company();
        company.setId(1L);
        company.setName("C1");
//		company.setGroupId(group.getGroupId());
        companyRepo.save(company);
        group.getCompanies().add(company);
        groupRepo.save(group);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        Group group1 = groupRepo.findById(1L).get();
        Set<Company> companies = group1.getCompanies();
        for (Company co : companies) {
            System.out.println(co.getId());
            System.out.println(co.getName());
        }

        Group gr = new Group();
        gr.setName("Proxym");
        gr.setGroupId(1L);
        gr.setCompanies(Set.of(new Company(1L)));

        groupRepo.save(gr);

        Group gr2 = groupRepo.findById(1L).get();
        companies = gr2.getCompanies();
        for (Company co : companies) {
            System.out.println(co.getId());
            System.out.println(co.getName());
            assertThat(co.getId()).isEqualTo(1L);
            assertThat(co.getName()).isEqualTo("C1");
        }

        session.getTransaction().commit();
        session.close();

    }

}
