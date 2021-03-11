package com.epam.task.sixth.entity;

public class Wagon implements Runnable {

    private int id;
    private boolean hasPerishableFood;
    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasPerishableFood() {
        return hasPerishableFood;
    }

    public void setHasPerishableFood(boolean hasPerishableFood) {
        this.hasPerishableFood = hasPerishableFood;
    }

    public Wagon(int id, boolean hasPerishableFood, boolean loaded) {
        this.id = id;
        this.hasPerishableFood = hasPerishableFood;
        this.loaded = loaded;
    }

    public Wagon() {
    }

    @Override
    public String toString() {
        return "WAGON{" +
                "id=" + id +
                ", hasPerishableFood=" + hasPerishableFood +
                ", isLoaded=" + loaded +
                '}';
    }


    @Override
    public void run() {
        Base base = Base.getInstance();
        try {
            base.addWagon(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
