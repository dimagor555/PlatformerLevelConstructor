package ru.dimagor555.levelconstructor.level.entities;

public class Ground extends Entity {

    public Ground(int x, int y) {
        type = EntityType.GROUND;
        width = 2;
        height = 2;
        xPosition = x;
        yPosition = y;
    }
}
