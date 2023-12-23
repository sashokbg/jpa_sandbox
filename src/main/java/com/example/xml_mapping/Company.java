package com.example.xml_mapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class Company {
    private Long id;

    private String name;

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Company() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
