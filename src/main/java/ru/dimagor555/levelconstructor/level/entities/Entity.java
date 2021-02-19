package ru.dimagor555.levelconstructor.level.entities;

public abstract class Entity {

    public EntityType type;
    public int xPosition;
    public int yPosition;
    public int width;
    public int height;
    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public enum EntityType {
        PLATFORM, GROUND, PLAYER, ENEMY, BARRIER;
    }
}
