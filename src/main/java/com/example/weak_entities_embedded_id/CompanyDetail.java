package com.example.weak_entities_embedded_id;

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

    String detail;

    @Id
    @ManyToOne
    private Company company;

    public CompanyDetail() {
    }

    public CompanyDetail(String service, String detail) {
        this.detail = detail;
        this.service = service;
    }
}
