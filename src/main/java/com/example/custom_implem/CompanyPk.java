package com.example.custom_implem;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
class CompanyPk implements Serializable {
    private Long id;

    public CompanyPk(Long id) {
        this.id = id;
    }
}
