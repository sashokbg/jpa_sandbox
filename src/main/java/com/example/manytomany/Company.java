package com.example.manytomany;

import jakarta.persistence.*;

@Entity
@Table(name = "corporate_company")
public class Company {
    @Id
    private Long id;

    @Column
    private String name;

    public Company(Long id) {
        this.id = id;
    }

    public Company() {
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
