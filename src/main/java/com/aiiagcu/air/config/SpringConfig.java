package com.aiiagcu.air.config;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
}
