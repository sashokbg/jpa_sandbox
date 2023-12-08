package com.example.elements_collection;


import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, CompanyPk> {
}
