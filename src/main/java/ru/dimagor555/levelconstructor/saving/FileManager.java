package ru.dimagor555.levelconstructor.saving;

import javafx.stage.FileChooser;
import ru.dimagor555.levelconstructor.Main;

import java.io.File;
import java.nio.file.Paths;

public class FileManager {

    public static File openFile() {
        File currDir = new File(Paths.get(".").toAbsolutePath().normalize().toString());

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open level");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Level files (*.lvl)", "*.lvl");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(currDir);

        return fileChooser.showOpenDialog(Main.stage);
    }
}
