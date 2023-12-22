package com.example.weak_entities_embedded_id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@NoArgsConstructor
public class CompanyDetailPk implements Serializable {
    Company company;

    String service;

    @Transient
    public Company getCompany() {
        return company;
    }

    @Transient
    public String getService() {
        return service;
    }
}
