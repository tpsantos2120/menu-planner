package com.nutrily.menuplanner;

import org.springframework.boot.SpringApplication;

public class TestMenuPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
