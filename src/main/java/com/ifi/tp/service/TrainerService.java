package com.ifi.tp.service;

import com.ifi.tp.bo.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> getAllTrainers();
    Trainer getTrainer(String name);
    void putTrainer(Trainer trainer);
}
