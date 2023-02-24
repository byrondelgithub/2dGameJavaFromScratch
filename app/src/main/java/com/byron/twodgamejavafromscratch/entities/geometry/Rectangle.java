package com.byron.twodgamejavafromscratch.entities.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.byron.twodgamejavafromscratch.entities.GameEntity;

public abstract class Rectangle extends GameEntity {

    protected float width;
    protected float height;
    protected Paint paint;

    public Rectangle(float positionX, float positionY, float width, float height) {
        super(positionX, positionY);
        this.width = width;
        this.height = height;
    }

    public boolean getCollideWith(float left, float top, float right, float bottom){
        return (left > (positionX - width/2) && top < (positionY + height/2) && right < (positionX + width/2) && bottom > (positionY - height/2));
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawRect(new Rect((int) (positionX - width/2), (int) (positionY + height/2), (int) (positionX + width/2), (int) (positionY - height/2)), paint);
    }

}
