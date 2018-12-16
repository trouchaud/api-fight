package com.ifi.tp.service;

import com.ifi.tp.bo.Fight;
import com.ifi.tp.repository.FightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FightServiceImpl implements FightService {

    private FightRepository fightRepository;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @Override
    public Iterable<Fight> getAllFights() {
        return this.fightRepository.findAll();
    }

    @Override
    public void setFight(Fight fight){
        this.fightRepository.save(fight);
    }
}
