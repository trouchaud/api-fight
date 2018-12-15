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
            Fight f1 = new Fight();
            f1.setNameFighter1("Ash");
            f1.setNameFighter2("Misty");
            f1.setWinner("Ash");

            Detail detail = new Detail();
            detail.setRound(1);
            detail.setDescription("Pikachu atack");

            Detail detail2 = new Detail();
            detail2.setRound(2);
            detail2.setDescription("Strami atack");

            Detail detail3 = new Detail();
            detail3.setRound(3);
            detail3.setDescription("fight over, Ash win");

            f1.setDetails(List.of(detail, detail2, detail3));

            Fight f2 = new Fight();
            f2.setNameFighter1("Misty");
            f2.setNameFighter2("Ash");
            f2.setWinner("Misty");

            detail = new Detail();
            detail.setRound(1);
            detail.setDescription("Raichu atack");

            detail2 = new Detail();
            detail2.setRound(2);
            detail2.setDescription("Strami atack");

            detail3 = new Detail();
            detail3.setRound(3);
            detail3.setDescription("fight over, Misty win");

            f2.setDetails(List.of(detail, detail2, detail3));

            fightRepository.save(f1);
            fightRepository.save(f2);
        };
    }
}
