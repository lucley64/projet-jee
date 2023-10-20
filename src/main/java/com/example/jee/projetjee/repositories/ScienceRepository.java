package com.example.jee.projetjee.repositories;

import com.example.jee.projetjee.data.Science;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScienceRepository extends CrudRepository<Science, Long> {

    Optional<Science> findByName(String name);
}