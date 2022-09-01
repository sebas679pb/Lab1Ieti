package edu.escuelaing.ieti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "edu.escuelaing.ieti" })
@SpringBootApplication
public class SpringAPIApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringAPIApplication.class, args);
    }
    
}
