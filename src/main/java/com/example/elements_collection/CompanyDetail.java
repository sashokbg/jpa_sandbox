package com.example.elements_collection;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class CompanyDetail {
    private String service;
    private String detail;
}
