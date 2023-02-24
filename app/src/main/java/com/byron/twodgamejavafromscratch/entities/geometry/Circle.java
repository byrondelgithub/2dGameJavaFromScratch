package com.byron.twodgamejavafromscratch.entities.geometry;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.byron.twodgamejavafromscratch.entities.GameEntity;

public abstract class Circle extends GameEntity {
    protected float radius;
    protected Drawable sprite;

    public Circle(float positionX, float positionY, float radius, Drawable sprite) {
        super(positionX, positionY);
        this.radius = radius;

        this.sprite = sprite;
    }

    @Override
    public void draw(Canvas canvas){
        this.sprite.setBounds((int)(positionX-radius), (int)(positionY-radius), (int)(positionX+radius), (int)(positionY+radius));
        sprite.draw(canvas);
    }

    public float getRadius() {
        return radius;
    }
}
