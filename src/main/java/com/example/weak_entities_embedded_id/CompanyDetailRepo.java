package com.example.weak_entities_embedded_id;

import org.springframework.data.repository.CrudRepository;

interface CompanyDetailRepo extends CrudRepository<CompanyDetail, CompanyPk> {
}
