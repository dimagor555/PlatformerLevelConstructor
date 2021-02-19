package ru.dimagor555.levelconstructor.level.entities;

public class Platform extends Entity {

    public Platform(int x, int y) {
        type = EntityType.PLATFORM;
        width = 1;
        height = 1;
        xPosition = x;
        yPosition = y;
    }
}
