package com.leron.liquibase.repository;

import com.leron.liquibase.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT firstName FROM Person p WHERE p.firstName LIKE %:personName%")
    String findByName(String personName);
}
