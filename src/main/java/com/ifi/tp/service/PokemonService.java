package com.ifi.tp.service;

import com.ifi.tp.bo.PokemonType;

import java.util.List;

public interface PokemonService {

    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonType(int id);

}
