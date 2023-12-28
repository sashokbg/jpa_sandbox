package com.example.weak_entities_one_way;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(CompanyDetailPk.class)
class CompanyDetail {
    @Id
    String service;

    @MapsId("company")
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;

    String detail;

    public CompanyDetail() {
    }

    public CompanyDetail(String service, String detail) {
        this.detail = detail;
        this.service = service;
    }
}
