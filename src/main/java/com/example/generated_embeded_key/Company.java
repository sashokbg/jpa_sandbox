package com.example.generated_embeded_key;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.*;

@Entity
@Table(name = "corporate_company")
class Company {

    @Type(value = CompanyPKType.class)
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "prod"),
            type = MyGenerator.class)
    private CompanyPk id;

    @Column
    private String name;

    public Company(Long id, String name) {
        this.id = new CompanyPk(String.valueOf(id));
        this.name = name;
    }

    protected Company() {
    }

    public CompanyPk getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
