package com.example.weak_entities_one_way;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(CompanyDetailPk.class)
class CompanyDetail {
    @Id
    @Column(name = "company_id")
    Long companyId;

    @Id
    String service;

    String detail;

    @Id
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public CompanyDetail() {
    }

    public CompanyDetail(String service, String detail) {
        this.detail = detail;
        this.service = service;
    }
}
