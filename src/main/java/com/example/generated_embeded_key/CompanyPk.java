package com.example.generated_embeded_key;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

class CompanyPk implements Serializable {

    private String id;

    protected CompanyPk() {
    }

    public CompanyPk(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyPk companyPk)) return false;
        return Objects.equals(id, companyPk.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
