package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.byron.twodgamejavafromscratch.entities.geometry.Circle;
import com.byron.twodgamejavafromscratch.entities.geometry.Semicircle;

public class Shield extends Semicircle {

    public Shield(float positionX, float positionY, float radius, float angle, float size) {
        super(positionX, positionY, radius, angle, size);
        this.paint.setColor(Color.WHITE);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(8);
    }

    public boolean getCollisionWith(Circle e){

        double angle2 = (angle+size/2) / 180 * Math.PI;
        double angle3 = (angle+size/2+size) / 180 * Math.PI;

        float point1X = positionX;
        float point1Y = positionY;

        float point2X = rotateX((float) (radius*1.5), angle2);
        float point2Y = rotateY((float) (radius*1.5), angle2);

        float point3X = rotateX((float) (radius*1.5), angle3);
        float point3Y = rotateY((float) (radius*1.5), angle3);


        double areaOrig = this.getAreaOfTriangle(point1X, point1Y, point2X, point2Y, point3X, point3Y);
        double area1 =    this.getAreaOfTriangle(point1X, point1Y, point2X, point2Y, e.positionX, e.positionY); //Math.abs( (point1X-e.positionX)*(point2Y-e.positionY) - (point2X-e.positionX)*(point1Y-e.positionY) );
        double area2 =    this.getAreaOfTriangle(point2X, point2Y, point3X, point3Y, e.positionX, e.positionY);
        double area3 =    this.getAreaOfTriangle(point3X, point3Y, point1X, point1Y, e.positionX, e.positionY);

        float distance = getDistanceBetweenObjects(this, e);
        distance -= e.getRadius() + radius;

        return distance <= 0 && ( (area1 + area2 + area3) <= areaOrig*1.5 && distance >= -16);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update() {
    }

    @Override
    public boolean isPressed(float x, float y) {
        return false;
    }

    @Override
    public void setActuator(float x, float y) {
    }

    @Override
    public void reset() {
    }

    @Override
    public void touchDown(float x, float y) {
    }

    @Override
    public void touchMove(float x, float y) {
    }

    @Override
    public void touchUp(float x, float y) {
    }
}
