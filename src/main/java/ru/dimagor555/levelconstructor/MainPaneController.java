package ru.dimagor555.levelconstructor;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ru.dimagor555.levelconstructor.level.Level;
import ru.dimagor555.levelconstructor.saving.FileManager;
import ru.dimagor555.levelconstructor.saving.LevelReader;
import ru.dimagor555.levelconstructor.saving.LevelSaver;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MainPaneController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button platformBtn;

    @FXML
    private Button enemyBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button barrierBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button newBtn;

    @FXML
    private Button openBtn;

    @FXML
    private Text coordinates;

    public MainPaneController() {
    }

    public MainPaneController(Pane root) {
        canvas = (Canvas) root.lookup("#canvas");
        platformBtn = (Button) root.lookup("#platformBtn");
        enemyBtn = (Button) root.lookup("#enemyBtn");
        deleteBtn = (Button) root.lookup("#deleteBtn");
        barrierBtn = (Button) root.lookup("#barrierBtn");
        saveBtn = (Button) root.lookup("#saveBtn");
        newBtn = (Button) root.lookup("#newBtn");
        openBtn = (Button) root.lookup("#openBtn");
        coordinates = (Text) root.lookup("#coordinates");

        canvas.setOnScroll(this::onCanvasScroll);
        canvas.setOnMouseClicked(mouseEvent ->
                Main.canvasGraphicProcessor.toolManager.onToolUsed());
        saveBtn.setOnAction(event -> LevelSaver.saveLevel(Main.currentLevel));
        openBtn.setOnAction(event -> openLevel());
        newBtn.setOnAction(event -> createNewLevel());
    }

    public void setToolHandlers() {
        var entityPlacer = Main.canvasGraphicProcessor.toolManager;
        platformBtn.setOnAction(event -> entityPlacer.setCurrentTool(ToolManager.Tool.PLATFORM));
        enemyBtn.setOnAction(event -> entityPlacer.setCurrentTool(ToolManager.Tool.ENEMY));
        deleteBtn.setOnAction(event -> entityPlacer.setCurrentTool(ToolManager.Tool.DELETE));
        barrierBtn.setOnAction(event -> entityPlacer.setCurrentTool(ToolManager.Tool.BARRIER));
        canvas.setOnMouseMoved(entityPlacer::setCurrentToolPos);
    }

    private void onCanvasScroll(ScrollEvent scrollEvent) {
        Main.canvasGraphicProcessor.canvasLayout.changeLayout(
                (int) scrollEvent.getDeltaX() * 4, (int) scrollEvent.getDeltaY());
    }

    private void openLevel() {
        Level readLevel = null;
        try {
            File file = FileManager.openFile();
            if (file != null) {
                readLevel = LevelReader.readLevel(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readLevel != null) {
            Main.currentLevel = readLevel;
            Main.canvasGraphicProcessor.canvasLayout.resetLayout();
            Main.canvasGraphicProcessor.start();
        }
    }

    private void createNewLevel() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Create new level");
        dialog.setHeaderText("Enter level name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (result.get().length() > 0) {
                Main.currentLevel = new Level(result.get());
                Main.canvasGraphicProcessor.canvasLayout.resetLayout();
                Main.canvasGraphicProcessor.start();
            }
        });
    }

    public void displayCoordinates(int x, int y) {
        String coordinates = String.format("x: %d\ny: %d", x, y);
        this.coordinates.setText(coordinates);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
