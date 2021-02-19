package ru.dimagor555.levelconstructor.saving;

import ru.dimagor555.levelconstructor.CanvasGraphicProcessor;
import ru.dimagor555.levelconstructor.level.Level;
import ru.dimagor555.levelconstructor.level.entities.Entity;
import ru.dimagor555.levelconstructor.level.entities.EntityFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LevelReader {

    public static Level readLevel(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        String fileInput = "";
        int currChar;
        do {
            currChar = fileReader.read();
            fileInput += (char) currChar;
        } while (currChar != -1);

        String[] input = fileInput.split("\n");
        String name = input[0].split("=")[1];
        Level readLevel = new Level(name);

        EntityFactory entityFactory = new EntityFactory();
        for (int i = 1; i < input.length; i++) {
            String[] entityParams = input[i].split(";");
            var type = Entity.EntityType.valueOf(entityParams[0].split("=")[1]);
            int x = Integer.parseInt(entityParams[1].split("=")[1]);
            int y = Integer.parseInt(entityParams[2].split("=")[1]);
            readLevel.addEntity(entityFactory.getEntity(type,
                    x * CanvasGraphicProcessor.ENTITY_SIZE_COEF,
                    y  * CanvasGraphicProcessor.ENTITY_SIZE_COEF));
        }

        return readLevel;
    }
}
