package com.example.weak_entities_one_way;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
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
