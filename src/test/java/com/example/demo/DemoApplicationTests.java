package com.example.demo;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private GroupRepo groupRepo;
	@Autowired
	private CompanyRepo companyRepo;
	private SessionFactory sessionFactory;

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
		groupRepo.save(group);

		Company company = new Company();
		company.setId(1L);
		company.setName("C1");
		company.setGroupId(group.getGroupId());
		companyRepo.save(company);

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
