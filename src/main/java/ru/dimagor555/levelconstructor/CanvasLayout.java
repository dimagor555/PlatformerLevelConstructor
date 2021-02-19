package ru.dimagor555.levelconstructor;

public class CanvasLayout {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private volatile int layoutX = 0;
    private volatile int layoutY = 0;

    public void changeLayout(int valueX, int valueY) {
        changeXLayout(valueX);
        changeYLayout(valueY);
//        markEntitiesVisible();
    }

    private void changeXLayout(int value) {
        layoutX += value;
        if (layoutX < 0) {
            layoutX = 0;
        } else if (layoutX > WIDTH * 100) {
            layoutX = WIDTH * 100;
        }
    }

    private void changeYLayout(int value) {
        layoutY += value;
        if (layoutY > HEIGHT * 3) {
            layoutY = HEIGHT * 3;
        } else if (layoutY < 0) {
            layoutY = 0;
        }
    }

    /*private void markEntitiesVisible() {
        markEntitiesVisible(Main.currentLevel.entities);
    }

    public void markEntitiesVisible(ArrayList<Entity> entities) {
        for (Entity entity :
                entities) {
            if (isEntityVisible(entity)) {
                entity.setVisible(true);
            } else {
                entity.setVisible(false);
            }
        }
    }*/

    /*private boolean isEntityVisible(Entity entity) {
        int lowLimitX = layoutX;
        int highLimitX = layoutX + WIDTH;
        int lowLimitY = layoutY;
        int highLimitY = layoutY + HEIGHT;

        int entitySizeCoef = CanvasGraphicProcessor.ENTITY_SIZE_COEF;
        int entityPosX = entity.xPosition;
        int entityPosY = entity.yPosition;
        int[] entitySizesX = new int[] {entityPosX, entityPosX + entity.width * entitySizeCoef};
        int[] entitySizesY = new int[] {entityPosY, entityPosY + entity.height * entitySizeCoef};

        for (int i = 0; i < entitySizesX.length; i++) {
            for (int j = 0; j < entitySizesY.length; j++) {
                int entityX = entitySizesX[i];
                int entityY = entitySizesY[j];
                if (entityX > lowLimitX && entityX < highLimitX
                        && entityY > lowLimitY && entityY < highLimitY) {
                    return true;
                }
            }
        }

        return false;
    }*/

    public void resetLayout() {
        layoutX = 0;
        layoutY = 0;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }
}
