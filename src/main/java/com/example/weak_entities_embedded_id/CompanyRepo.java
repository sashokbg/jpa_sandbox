package com.example.weak_entities_embedded_id;


import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, CompanyPk> {
}
