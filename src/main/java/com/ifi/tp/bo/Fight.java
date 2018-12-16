package com.ifi.tp.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fight {

    @Id
    @GeneratedValue
    private int id;

    private String nameFighter1;

    private String nameFighter2;

    private String winner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Detail> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFighter1() {
        return nameFighter1;
    }

    public void setNameFighter1(String nameFighter1) {
        this.nameFighter1 = nameFighter1;
    }

    public String getNameFighter2() {
        return nameFighter2;
    }

    public void setNameFighter2(String nameFighter2) {
        this.nameFighter2 = nameFighter2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public void atack(Pokemon pok1, Pokemon pok2){
        PokemonType pok = pok1.getType();
        PokemonType pokEnemie = pok2.getType();
        int atack = pok1.getLevel()+pok.getStats().getAttack();
        int defense = pok2.getLevel()+pokEnemie.getStats().getDefense();
        int damage = 1;
        int health = pok2.getHp();
        List<Detail> details = new ArrayList<>(this.getDetails());
        Detail lastDetail = details.get(details.size()-1);
        Detail newDetail = new Detail();
        newDetail.setRound(lastDetail.getRound()+1);

        String description = pok1.getType().getName()+" atack "+pok2.getType().getName()+".";

        if(atack>defense){
            damage = atack - defense;
        }
        description+="He give him "+damage+" damage.";

        health = health - damage;
        if(health < 0){
            health = 0;
            description+=pok2.getType().getName()+" is ko.";
        }

        newDetail.setDescription(description);
        details.add(newDetail);
        this.setDetails(details);

        pok2.setHp(health);
    }

    public void fight(Pokemon pok1, Pokemon pok2){
        if(pok2.getType().getStats().getSpeed()>pok1.getType().getStats().getSpeed()){
            Pokemon temp = new Pokemon();
            temp = pok1;
            pok1 = pok2;
            pok2 = temp;
        }

        while(true){
            this.atack(pok1,pok2);
            if(pok2.getHp() == 0){
                break;
            }
            this.atack(pok2,pok1);
            if(pok1.getHp() == 0){
                break;
            }
        }
    }
}
