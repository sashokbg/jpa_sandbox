package com.example.weak_entities_one_way;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class CompanyDetail {
    @EmbeddedId
    CompanyDetailPk key;

    String detail;

    public CompanyDetail() {
    }

    public CompanyDetail(String service, String detail) {
        this.detail = detail;
        this.key.service = service;
    }
}
