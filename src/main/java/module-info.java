module LevelConstructor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires javafx.swing;

    opens ru.dimagor555.levelconstructor to javafx.fxml;
    exports ru.dimagor555.levelconstructor;
}