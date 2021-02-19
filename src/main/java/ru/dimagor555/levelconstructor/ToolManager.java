package ru.dimagor555.levelconstructor;

import javafx.scene.input.MouseEvent;
import ru.dimagor555.levelconstructor.level.entities.Barrier;
import ru.dimagor555.levelconstructor.level.entities.Enemy;
import ru.dimagor555.levelconstructor.level.entities.Entity;
import ru.dimagor555.levelconstructor.level.entities.Platform;

public class ToolManager {

    private Tool currentTool;
    private int currToolX;
    private int currToolY;

    public int getCurrentToolX() {
        return currToolX;
    }

    public int getCurrentToolY() {
        return currToolY;
    }

    public void setCurrentToolPos(MouseEvent event) {
        int eventX = (int) Math.round(event.getX()) + Main.canvasGraphicProcessor.canvasLayout.getLayoutX();
        int eventY = (int) Math.round(CanvasLayout.HEIGHT - event.getY())
                + Main.canvasGraphicProcessor.canvasLayout.getLayoutY();

        int entitySizeCoef = CanvasGraphicProcessor.ENTITY_SIZE_COEF;

        int gridX = eventX - eventX % entitySizeCoef;
        int gridY = eventY - eventY % entitySizeCoef;
        if (eventY < 0) {
            gridY -= entitySizeCoef;
        }

        currToolX = gridX;
        currToolY = gridY;

        Main.mainPaneController.displayCoordinates(gridX / entitySizeCoef, gridY / entitySizeCoef);
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        if (currentTool == this.currentTool) {
            this.currentTool = null;
        } else {
            this.currentTool = currentTool;
        }
    }

    public void onToolUsed() {
        if (currentTool == Tool.DELETE) {
            deleteEntity();
        } else {
            placeEntity(currentTool);
        }
    }

    public void placeEntity(Tool entityType) {
        Entity entityToPlace;
        if (entityType == Tool.PLATFORM) {
            entityToPlace = new Platform(getCurrentToolX(), getCurrentToolY());
        } else if (entityType == Tool.ENEMY) {
            entityToPlace = new Enemy(getCurrentToolX(), getCurrentToolY());
        } else if (entityType == Tool.BARRIER) {
            entityToPlace = new Barrier(getCurrentToolX(), getCurrentToolY());
        } else {
            return;
        }
        if (!hasEntityOnCoordinates()) {
            Main.currentLevel.addEntity(entityToPlace);
        }
    }

    private boolean hasEntityOnCoordinates() {
        return getEntityUnderToolCursor() != null;
    }

    public void deleteEntity() {
        if (!hasEntityOnCoordinates()) {
            return;
        }
        Entity entity = getEntityUnderToolCursor();
        if (entity.type != Entity.EntityType.PLAYER && entity.type != Entity.EntityType.GROUND) {
            Main.currentLevel.removeEntity(getEntityUnderToolCursor());
        }
    }

    private Entity getEntityUnderToolCursor() {
        for (Entity entity :
                Main.currentLevel.entities) {
            int entitySizeCoef = CanvasGraphicProcessor.ENTITY_SIZE_COEF;

            int entityLowLimitX = entity.xPosition;
            int entityHighLimitX = entity.xPosition + entitySizeCoef * entity.width;
            int entityLowLimitY = entity.yPosition;
            int entityHighLimitY = entity.yPosition + entitySizeCoef * entity.height;

            int toolX = getCurrentToolX();
            int toolY = getCurrentToolY();

            if (toolX >= entityLowLimitX && toolX < entityHighLimitX
                    && toolY >= entityLowLimitY && toolY < entityHighLimitY) {
                return entity;
            }
        }
        return null;
    }


    enum Tool {
        PLATFORM, ENEMY, DELETE, BARRIER;
    }
}
