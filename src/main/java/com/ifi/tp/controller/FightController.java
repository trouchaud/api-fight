package com.ifi.tp.controller;

import com.ifi.tp.bo.*;
import com.ifi.tp.service.FightService;
import com.ifi.tp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/fights")
public class FightController {

    @Autowired
    private final FightService fightService;

    @Autowired
    private final TrainerService trainerService;

    FightController(FightService fightService, TrainerService trainerService){
        this.fightService = fightService;
        this.trainerService = trainerService;
    }

    @GetMapping("")
    Iterable<Fight> getAllFights(){
        return this.fightService.getAllFights();
    }

    @GetMapping("/{name1}/{name2}")
    Iterable<Fight> getAllFightsBetweenFighters(@PathVariable String name1,
                                                @PathVariable String name2){

        List<Fight> fight = new ArrayList<>();
        List<Fight> allFights = (List<Fight>) this.fightService.getAllFights();

        for (Iterator<Fight> iter = allFights.listIterator(); iter.hasNext(); ) {
            Fight p = iter.next();

            if (p.getNameFighter1().equals(name1)&&p.getNameFighter2().equals(name2)||
                    p.getNameFighter1().equals(name2)&&p.getNameFighter2().equals(name1)) {
                fight.add(p);
            }
        }

        return fight;
    }

    @GetMapping("/{name}")
    Iterable<Fight> getAllFightsForFighter(@PathVariable String name){

        List<Fight> fight = new ArrayList<>();
        List<Fight> allFights = (List<Fight>) this.fightService.getAllFights();

        for (Iterator<Fight> iter = allFights.listIterator(); iter.hasNext(); ) {
            Fight p = iter.next();

            if (p.getNameFighter1().equals(name)||
                    p.getNameFighter2().equals(name)) {
                fight.add(p);
            }
        }

        return fight;
    }

    @GetMapping("/engage/{name1}/{name2}")
    Iterable<Fight> setFight(@PathVariable String name1,
                               @PathVariable String name2){

        Trainer trainer1 = this.trainerService.getTrainer(name1);
        Trainer trainer2 = this.trainerService.getTrainer(name2);

        Fight fight = new Fight();
        fight.setNameFighter1(name1);
        fight.setNameFighter2(name2);

        Detail initFight = new Detail();
        initFight.setRound(1);
        initFight.setDescription(trainer1.getTeam().get(0).getType().getName()
                +" will fight "+
                trainer2.getTeam().get(0).getType().getName());

        fight.setDetails(List.of(initFight));

        fight.fight(trainer1.getTeam().get(1),trainer2.getTeam().get(1));

        this.trainerService.putTrainer(trainer2);
        this.trainerService.putTrainer(trainer1);
        this.fightService.setFight(fight);

        return this.fightService.getAllFights();
    }

//    void fight(Pokemon pok1,Pokemon pok2){
//        boolean ko = false;
//        PokemonType pokType1 = pok1.getType();
//        PokemonType pokType2 = pok2.getType();
//
//        while(!ko){
//            if(pok.getStats().getSpeed() > pokEnemie.getStats().getSpeed()) {
//                this.atack(enemie);
//                enemie.atack(this);
//            }
//        }
//    }
}
