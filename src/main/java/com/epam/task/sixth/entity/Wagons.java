package com.epam.task.sixth.entity;

import java.util.List;

public class Wagons {
    public Wagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    private List<Wagon> wagons;

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    public Wagons() {
    }
}
