package com.byron.twodgamejavafromscratch.entities.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.byron.twodgamejavafromscratch.entities.GameEntity;

public abstract class Semicircle extends GameEntity {
    protected float radius;
    protected float angle;
    protected float size;
    protected Paint paint;

    public Semicircle(float positionX, float positionY, float radius, float angle, float size) {
        super(positionX, positionY);
        this.radius = radius;
        this.angle = angle;
        this.size = size;

        this.paint = new Paint();
    }

    public float getAngle() {
        return angle;
    }

    public void setPosition(float positionX, float positionY, float angle){
        this.positionX = positionX;
        this.positionY = positionY;
        this.angle = angle;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public static double getAreaOfTriangle(float x1, float y1, float x2, float y2, float x3, float y3)
    {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawArc(positionX-radius, positionY-radius, positionX+radius, positionY+radius, angle+size/2, size, false, paint);

    }

    public float rotateX(float x, double angle){
        return (float)( (x * Math.cos(angle)) - (0 * Math.sin(angle)) ) + positionX;
    }

    public float rotateY(float y, double angle){
        return (float)( (y * Math.sin(angle)) + (0 * Math.sin(angle)) ) + positionY;
    }
}
