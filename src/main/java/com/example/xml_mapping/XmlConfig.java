package com.example.xml_mapping;

import jakarta.persistence.spi.PersistenceUnitInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class XmlConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);

        entityManagerFactoryBean.setPersistenceUnitRootLocation("classpath:META-INF/custom_persistence.xml");
        entityManagerFactoryBean.setPersistenceXmlLocation("classpath:META-INF/custom_orm.xml");
        entityManagerFactoryBean.setPersistenceUnitName("default");

        // Add other properties as needed
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }
}
