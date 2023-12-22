package com.example.encapsulation;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
class Company {
    @EmbeddedId
    private CompanyId id;

    @Column
    private String name;

    protected Company() {}

    public Company(CompanyId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
