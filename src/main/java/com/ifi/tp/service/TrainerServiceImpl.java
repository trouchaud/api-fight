package com.ifi.tp.service;

import com.ifi.tp.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private PokemonService pokemonService;
    private RestTemplate restTemplate;

    private String trainerServiceUrl;

    @Override
    public List<Trainer> getAllTrainers() {
        var url = trainerServiceUrl + "/trainers";
        var trainers = restTemplate.getForObject(url, Trainer[].class);

        return Arrays.asList(trainers);
    }

    @Override
    public Trainer getTrainer(String name) {
        var url = trainerServiceUrl + "/trainers/{name}";
        var trainer = restTemplate.getForObject(url, Trainer.class, name);
        return this.enrich(trainer);
    }

    @Override
    public void putTrainer(Trainer trainer) {
        var url = trainerServiceUrl + "/trainers";
        restTemplate.put(url, trainer);
    }

    private Trainer enrich(Trainer trainer){
        trainer.getTeam()
                .stream()
                .forEach(pokemon -> pokemon.setType(pokemonService.getPokemonType(pokemon.getPokemonNumber())));
        return trainer;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.trainerServiceUrl = pokemonServiceUrl;
    }

    @Autowired
    void setPokemonService(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
