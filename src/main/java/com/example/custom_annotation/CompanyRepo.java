package com.example.custom_annotation;


import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, CompanyPk> {
}
