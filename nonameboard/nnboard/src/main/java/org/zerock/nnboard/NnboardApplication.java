package org.zerock.nnboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NnboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(NnboardApplication.class, args);
    }

}
