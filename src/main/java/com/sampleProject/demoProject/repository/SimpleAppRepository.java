package com.sampleProject.demoProject.repository;

import com.sampleProject.demoProject.model.SimpleApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

    public interface SimpleAppRepository extends JpaRepository<SimpleApp, Long> {

    }



