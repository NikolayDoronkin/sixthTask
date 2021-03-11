package com.epam.task.sixth.entity;

public class Terminal {

    public void processing(Wagon wagon) {
        boolean isLoaded = wagon.isLoaded();
        wagon.setLoaded(!isLoaded);
    }

}
