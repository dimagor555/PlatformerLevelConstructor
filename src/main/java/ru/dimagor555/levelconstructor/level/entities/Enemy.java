package ru.dimagor555.levelconstructor.level.entities;

public class Enemy extends Entity {

    public Enemy(int x, int y) {
        width = 2;
        height = 4;
        xPosition = x;
        yPosition = y;
        type = EntityType.ENEMY;
    }
}
