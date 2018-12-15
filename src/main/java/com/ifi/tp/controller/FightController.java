package com.ifi.tp.controller;

import com.ifi.tp.bo.Trainer;
import com.ifi.tp.service.FightService;
import com.ifi.tp.service.ProductService;
import com.ifi.tp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fights")
public class FightController {

    @Autowired
    private final FightService fightService;

    FightController(ProductService productService, FightService trainerService){
        this.trainerService = trainerService;
        this.productService = productService;
    }

    @GetMapping("")
    Iterable<Fight> getAllFights(){
        return this.trainerService.getAllFights();
    }
}
