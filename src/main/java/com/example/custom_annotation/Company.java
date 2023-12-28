package com.example.custom_annotation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
class Company {

    private CompanyPk id;

    private String name;

    List<CompanyDetail> details = new ArrayList<>();

    public Company(Long id, String name) {
        this.id = new CompanyPk(id);
        this.name = name;
    }

}
