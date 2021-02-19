package ru.dimagor555.levelconstructor.level.entities;

public class Player extends Entity {

    public Player(int x, int y) {
        type = EntityType.PLAYER;
        width = 2;
        height = 4;
        xPosition = x;
        yPosition = y;
    }
}
