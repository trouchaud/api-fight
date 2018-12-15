package com.ifi.tp.bo;

import javax.persistence.*;
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
}
