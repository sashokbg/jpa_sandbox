package com.example.generated_embeded_key;

import com.example.PostgresTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = GeneratedEmbededKeyTest.class)
@EnableAutoConfiguration
@ContextConfiguration
public class GeneratedEmbededKeyTest extends PostgresTest {

    @Autowired
    CompanyRepo companyRepo;

    @Test
    public void embeddedKey() {
        Company company = new Company(null, "NAME 1");

        companyRepo.save(company);

        Company company2 = new Company(null, "NAME 2");
        companyRepo.save(company2);

        Optional<Company> byId = companyRepo.findById(new CompanyPk("prod-1"));

        assertThat(byId).isPresent();
    }
}
