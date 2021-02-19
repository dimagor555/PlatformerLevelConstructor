package ru.dimagor555.levelconstructor.level;

import ru.dimagor555.levelconstructor.level.entities.Entity;
import ru.dimagor555.levelconstructor.level.entities.Ground;
import ru.dimagor555.levelconstructor.level.entities.Player;

import java.util.ArrayList;

public class Level {

    public String name = "1";

    public ArrayList<Entity> entities = new ArrayList<>();

    public Level(String name) {
        this.name = name;
        for (int i = 0; i < 100 * 1280 / 40; i++) {
            addEntity(new Ground(i * 80, 0));
        }
        addEntity(new Player(80, 80));
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
