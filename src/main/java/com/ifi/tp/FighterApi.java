package com.ifi.tp;

import com.ifi.tp.bo.Pokemon;
import com.ifi.tp.bo.Product;
import com.ifi.tp.bo.ProductType;
import com.ifi.tp.bo.Trainer;
import com.ifi.tp.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FighterApi {

    public static void main(String... args){
        SpringApplication.run(FighterApi.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner demo(
            FighterRepository repository) {
        return (args) -> {
        };
    }

}
