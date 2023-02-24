package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.byron.twodgamejavafromscratch.entities.geometry.Rectangle;

public class Ring extends Rectangle {

    public Ring(float positionX, float positionY, float width, float height) {
        super(positionX, positionY, width, height);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
    }

    @Override
    public void draw(Canvas canvas){
        this.positionX = canvas.getWidth() / 2;
        this.positionY = canvas.getHeight() / 2;
        this.width = canvas.getWidth() * 0.5f;
        this.height = canvas.getHeight() * 0.6f;

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
