package com.ifi.tp.controller;

import com.ifi.tp.bo.Fight;
import com.ifi.tp.service.FightService;
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

    FightController(FightService fightService){
        this.fightService = fightService;
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
}
