package com.example.onetomany;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "corporate_company")
class Company {
    @Id
    private Long id;

    @Column
    private String name;

    @Column(name = "group_id")
    private Long groupId;

    @ElementCollection
    List<Rule> rules;

    @OneToMany(cascade = CascadeType.ALL)
    List<CompanyDetails> companyDetails = new ArrayList<>();

    public Company(Long id) {
        this.id = id;
      rules = new ArrayList<>();
    }

    public Company() {
      rules = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
