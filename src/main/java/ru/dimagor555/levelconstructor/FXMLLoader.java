package ru.dimagor555.levelconstructor;

import javafx.scene.layout.Pane;
import java.io.IOException;

public class FXMLLoader {

    public Pane loadPane(String pathToFile) {
        var path = getClass().getResource(pathToFile);
        try {
            return javafx.fxml.FXMLLoader.load(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
