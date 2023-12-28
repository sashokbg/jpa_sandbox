package com.example.custom_repo_implem;


import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, CompanyPk>, CompanyRepoCustom {
}
