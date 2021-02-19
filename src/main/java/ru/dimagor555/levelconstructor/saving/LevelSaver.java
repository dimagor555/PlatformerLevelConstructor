package ru.dimagor555.levelconstructor.saving;

import ru.dimagor555.levelconstructor.CanvasGraphicProcessor;
import ru.dimagor555.levelconstructor.level.Level;
import ru.dimagor555.levelconstructor.level.entities.Entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LevelSaver {

    public static void saveLevel(Level level) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Name=%s", level.name));
        for (Entity entity :
                level.entities) {
            if (entity.type == Entity.EntityType.GROUND
                    || entity.type == Entity.EntityType.PLAYER) {
                continue;
            }
            String type = entity.type.name();
            int x = entity.xPosition / CanvasGraphicProcessor.ENTITY_SIZE_COEF;
            int y = entity.yPosition / CanvasGraphicProcessor.ENTITY_SIZE_COEF;
            output.append(String.format("\nType=%s;x=%d;y=%d;", type, x, y));
        }
        try {
            writeToFile(level.name, output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String name, String output) throws IOException {
        File outputFile = new File(name + ".lvl");
        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(output);
        fileWriter.flush();
        fileWriter.close();
    }
}
