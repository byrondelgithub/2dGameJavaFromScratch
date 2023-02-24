package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;

public abstract class GameEntity {
    protected float positionX;
    protected float positionY;
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected int pointerId;
    protected boolean isTouched = false;

    public GameEntity(float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static float getDistanceBetweenObjects(GameEntity obj1, GameEntity obj2) {
        return (float)Math.sqrt(
                Math.pow((double) (obj2.getPositionX()-obj1.getPositionX()), 2) +
                        Math.pow((double) (obj2.getPositionY()-obj1.getPositionY()), 2)
        );
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public int getPointerId() {
        return pointerId;
    }

    public void setPointerId(int pointerId) {
        this.pointerId = pointerId;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public abstract boolean isPressed(float x, float y);
    public abstract void setActuator(float x, float y);
    public abstract void reset();

    public abstract void touchDown(float x, float y);
    public abstract void touchMove(float x, float y);
    public abstract void touchUp(float x, float y);
}
