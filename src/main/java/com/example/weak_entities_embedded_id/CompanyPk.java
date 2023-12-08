package com.example.weak_entities_embedded_id;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
class CompanyPk implements Serializable {
    private Long id;

    public CompanyPk(Long id) {
        this.id = id;
    }
}
