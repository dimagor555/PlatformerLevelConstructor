package ru.dimagor555.levelconstructor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.dimagor555.levelconstructor.level.entities.Entity;

public class CanvasPainter {

    private GraphicsContext gc;

    public CanvasPainter(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawEntity(Entity entity) {
        if (entity.type == Entity.EntityType.PLATFORM) {
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
        } else if (entity.type == Entity.EntityType.GROUND) {
            gc.setFill(Color.GREEN.darker());
            gc.setStroke(Color.GREEN.darker());
        } else if (entity.type == Entity.EntityType.PLAYER) {
            gc.setFill(Color.BLUE);
            gc.setStroke(Color.BLUE);
        } else {
            gc.setFill(Color.RED);
            gc.setStroke(Color.RED);
        }

        int layoutX = Main.canvasGraphicProcessor.canvasLayout.getLayoutX();
        int layoutY = Main.canvasGraphicProcessor.canvasLayout.getLayoutY();
        int entitySizeCoef = CanvasGraphicProcessor.ENTITY_SIZE_COEF;

        drawRectangle(entity.xPosition - layoutX,
                (CanvasLayout.HEIGHT - entity.yPosition) + layoutY - entity.height * entitySizeCoef,
                entity.width * entitySizeCoef, entity.height * entitySizeCoef);
    }

    public void drawDeleteCursor(int x, int y) {
        gc.setFill(Color.CYAN);
        gc.setStroke(Color.CYAN);

        int layoutX = Main.canvasGraphicProcessor.canvasLayout.getLayoutX();
        int layoutY = Main.canvasGraphicProcessor.canvasLayout.getLayoutY();
        int entitySizeCoef = CanvasGraphicProcessor.ENTITY_SIZE_COEF;

        drawRectangle(x + entitySizeCoef / 4 - layoutX,
                (CanvasLayout.HEIGHT - y) + entitySizeCoef / 4 + layoutY - entitySizeCoef,
                entitySizeCoef / 2, entitySizeCoef / 2);
    }

    public void drawBG() {
        gc.setFill(Color.GRAY.brighter());
        gc.setStroke(Color.GRAY.brighter());
        gc.fillRect(0, 0, CanvasLayout.WIDTH, CanvasLayout.HEIGHT);
    }

    public void clear() {
        gc.clearRect(0, 0, CanvasLayout.WIDTH, CanvasLayout.HEIGHT);
    }

    private void drawRectangle(int x, int y, int width, int height) {
        gc.fillRect(x, y, width, height);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2d);
        gc.beginPath();
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }
}
