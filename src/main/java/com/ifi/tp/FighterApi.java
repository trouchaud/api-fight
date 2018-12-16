package com.ifi.tp;

import com.ifi.tp.bo.Detail;
import com.ifi.tp.bo.Fight;
import com.ifi.tp.repository.DetailRepository;
import com.ifi.tp.repository.FightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FighterApi {

    public static void main(String... args) {
        SpringApplication.run(FighterApi.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner demo(
            FightRepository fightRepository, DetailRepository detailRepository) {
        return (args) -> {

        };
    }
}
