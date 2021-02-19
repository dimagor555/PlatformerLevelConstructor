package ru.dimagor555.levelconstructor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.dimagor555.levelconstructor.level.Level;

public class Main extends Application {

    public static final String PATH_TO_MAIN_PANE = "/fxml/Main.fxml";

    public static Stage stage;
    public static Pane root;
    public static MainPaneController mainPaneController;
    public static CanvasGraphicProcessor canvasGraphicProcessor;
    public static Level currentLevel;
    public static FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = loader.loadPane(PATH_TO_MAIN_PANE);
        mainPaneController = new MainPaneController(root);
        canvasGraphicProcessor = new CanvasGraphicProcessor(mainPaneController.getCanvas());
        mainPaneController.setToolHandlers();
        var scene = new Scene(root);

        primaryStage = new Stage();
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level constructor");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
