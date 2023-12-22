package com.example.encapsulation;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
class CompanyId implements Serializable {
    private String value;

    protected CompanyId() {}

    public CompanyId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
