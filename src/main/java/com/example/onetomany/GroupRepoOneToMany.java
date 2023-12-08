package com.example.onetomany;


import org.springframework.data.repository.CrudRepository;

interface GroupRepoOneToMany extends CrudRepository<Group, Long> {
}
