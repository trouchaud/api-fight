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
    Fight setFight(@PathVariable String name1,
                               @PathVariable String name2){

        Trainer trainer1 = this.trainerService.getTrainer(name1);
        Trainer trainer2 = this.trainerService.getTrainer(name2);

        List<Pokemon> team1 = new ArrayList<>();
        List<Pokemon> team2 = new ArrayList<>();

        for (Iterator<Pokemon> iter = trainer1.getTeam().listIterator(); iter.hasNext(); ) {
            Pokemon pok = iter.next();

            if (pok.getHp() > 0) {
                team1.add(pok);
            }
        }

        for (Iterator<Pokemon> iter = trainer2.getTeam().listIterator(); iter.hasNext(); ) {
            Pokemon pok = iter.next();

            if (pok.getHp() > 0) {
                team2.add(pok);
            }
        }

        Fight fight = new Fight();
        fight.setNameFighter1(name1);
        fight.setNameFighter2(name2);

        Detail initFight = new Detail();
        initFight.setRound(1);
        initFight.setDescription(team1.get(0).getType().getName()
                +" will fight "+
                team2.get(0).getType().getName());

        fight.setDetails(List.of(initFight));

        int sizeTeam1 = team1.size();
        int sizeTeam2 = team2.size();
        int i=0, j=0;

        while (i<sizeTeam1&&j<sizeTeam2){
            fight.fight(team1.get(i),team2.get(j));
            if(team1.get(i).getHp()==0){
                i++;
            }
            if(team2.get(j).getHp()==0){
                j++;
            }
        }

        if(i==sizeTeam1) i--;
        if(j==sizeTeam2) j--;

        if(team1.get(i).getHp() == 0){
            fight.setWinner(name2);
        }
        else{
            fight.setWinner(name1);
        }

        this.trainerService.putTrainer(trainer2);
        this.trainerService.putTrainer(trainer1);
        this.fightService.setFight(fight);

        return fight;
    }
}
