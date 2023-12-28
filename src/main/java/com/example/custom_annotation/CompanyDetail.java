package com.example.custom_annotation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CompanyDetail extends WeakEntity<CompanyDetailPk> {
    Company companyId;

    String service;

    String detail;

    protected CompanyDetail() {
        super();
    }

    public CompanyDetail(Company companyId, String service, String detail) {
        this.companyId = companyId;
        this.service = service;
        this.detail = detail;
    }
}
