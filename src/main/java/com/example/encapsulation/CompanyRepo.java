package com.example.encapsulation;

import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, CompanyId> {
}
