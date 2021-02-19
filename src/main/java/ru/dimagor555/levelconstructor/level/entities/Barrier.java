package ru.dimagor555.levelconstructor.level.entities;

public class Barrier extends Entity {

    public Barrier(int x, int y) {
        type = EntityType.BARRIER;
        width = 2;
        height = 2;
        xPosition = x;
        yPosition = y;
    }
}
