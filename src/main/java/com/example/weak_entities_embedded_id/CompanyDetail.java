package com.example.weak_entities_embedded_id;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(CompanyDetailPk.class)
class CompanyDetail {
    @Column(name = "company_id")
    @Id
    Long companyId;

    @Id
    String service;

    String detail;

    @MapsId("company_id")
    @ManyToOne
    private Company company;

    public CompanyDetail() {
    }

    public CompanyDetail(String service, String detail) {
        this.detail = detail;
        this.service = service;
    }
}
