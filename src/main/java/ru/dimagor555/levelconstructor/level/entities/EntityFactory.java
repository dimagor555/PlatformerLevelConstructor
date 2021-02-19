package ru.dimagor555.levelconstructor.level.entities;

public class EntityFactory {

    public Entity getEntity(Entity.EntityType type, int x, int y) {
        Entity entity = null;
        switch (type) {
            case PLATFORM:
                entity = new Platform(x, y);
                break;
            case ENEMY:
                entity = new Enemy(x, y);
                break;
            case BARRIER:
                entity = new Barrier(x, y);
        }
        return entity;
    }
}
