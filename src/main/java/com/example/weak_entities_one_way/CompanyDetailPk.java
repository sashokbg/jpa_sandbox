package com.example.weak_entities_one_way;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
class CompanyDetailPk implements Serializable {
    @ManyToOne
    Company company;

    String service;
}
