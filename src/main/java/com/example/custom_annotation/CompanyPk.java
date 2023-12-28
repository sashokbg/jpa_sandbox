package com.example.custom_annotation;

import java.io.Serializable;

class CompanyPk implements Serializable {
    private Long id;

    public CompanyPk(Long id) {
        this.id = id;
    }
}
