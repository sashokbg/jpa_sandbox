package com.example.onetomany;


import org.springframework.data.repository.CrudRepository;

public interface GroupRepoOneToMany extends CrudRepository<Group, Long> {
}
