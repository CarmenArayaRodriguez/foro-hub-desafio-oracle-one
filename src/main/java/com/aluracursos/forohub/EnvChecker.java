package com.aluracursos.forohub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EnvChecker {

    @Autowired
    private Dotenv dotenv;

    @PostConstruct
    public void checkEnv() {
        System.out.println("DB_URL: " + dotenv.get("DB_URL"));
        System.out.println("DB_USERNAME: " + dotenv.get("DB_USERNAME"));
        System.out.println("DB_PASSWORD: " + dotenv.get("DB_PASSWORD"));
    }
}
