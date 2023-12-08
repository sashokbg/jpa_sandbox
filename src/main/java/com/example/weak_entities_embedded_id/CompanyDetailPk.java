package com.example.weak_entities_embedded_id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CompanyDetailPk implements Serializable {
    @Column(name = "company_id")
    Long companyId;

    String service;
}
