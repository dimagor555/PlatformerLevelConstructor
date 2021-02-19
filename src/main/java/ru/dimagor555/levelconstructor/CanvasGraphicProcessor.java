package ru.dimagor555.levelconstructor;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import ru.dimagor555.levelconstructor.level.entities.Barrier;
import ru.dimagor555.levelconstructor.level.entities.Enemy;
import ru.dimagor555.levelconstructor.level.entities.Entity;

public class CanvasGraphicProcessor {

    public static final int ENTITY_SIZE_COEF = 40;

    public CanvasLayout canvasLayout = new CanvasLayout();
    public CanvasPainter canvasPainter;
    public ToolManager toolManager = new ToolManager();

    private Canvas canvas;
    private Thread updateThread;

    public CanvasGraphicProcessor(Canvas canvas) {
        this.canvas = canvas;
        canvasPainter = new CanvasPainter(canvas.getGraphicsContext2D());
    }

    public void start() {
        if (!isStarted()) {
//            canvasLayout.markEntitiesVisible(Main.currentLevel.entities);
            updateThread = new Thread(this::update);
            updateThread.start();
        }
    }

    private boolean isStarted() {
        return updateThread != null;
    }

    private void update() {
        while (true) {
            var entities = Main.currentLevel.entities;
            Platform.runLater(() -> {
                canvasPainter.clear();
                canvasPainter.drawBG();
                for (Entity entity :
                        entities) {
                    if (entity.isVisible()) {
                        canvasPainter.drawEntity(entity);
                    }
                }
                drawTool();
                drawGrid();
            });
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawTool() {
        var tool = toolManager.getCurrentTool();
        if (tool == null) {
            return;
        }
        int x = toolManager.getCurrentToolX();
        int y = toolManager.getCurrentToolY();
        Entity toolCursor;
        switch (tool) {
            case PLATFORM:
                toolCursor = new ru.dimagor555.levelconstructor.level.entities.Platform(x, y);
                canvasPainter.drawEntity(toolCursor);
                break;
            case ENEMY:
                toolCursor = new Enemy(x, y);
                canvasPainter.drawEntity(toolCursor);
                break;
            case DELETE:
                canvasPainter.drawDeleteCursor(x, y);
                break;
            case BARRIER:
                toolCursor = new Barrier(x, y);
                canvasPainter.drawEntity(toolCursor);
                break;
        }
    }

    private void drawGrid() {
        int horizontalLineCount = CanvasLayout.HEIGHT * 3 / ENTITY_SIZE_COEF;
        int verticalLineCount = CanvasLayout.WIDTH * 100 / ENTITY_SIZE_COEF;
        for (int i = 0; i < horizontalLineCount; i++) {
            int x1 = 0;
            int x2 = CanvasLayout.WIDTH * 100;
            int y = i * ENTITY_SIZE_COEF - 1;
            canvasPainter.drawLine(x1, y, x2, y);
        }
        for (int i = 0; i < verticalLineCount; i++) {
            int y1 = -CanvasLayout.HEIGHT * 2;
            int y2 = CanvasLayout.HEIGHT;
            int x = i * ENTITY_SIZE_COEF - 1;
            canvasPainter.drawLine(x, y1, x, y2);
        }
    }
}
