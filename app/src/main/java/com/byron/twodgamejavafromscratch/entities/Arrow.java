package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.byron.twodgamejavafromscratch.Game;
import com.byron.twodgamejavafromscratch.GameLoop;
import com.byron.twodgamejavafromscratch.entities.geometry.Circle;

public class Arrow extends Circle {
    private final Player player;
    private final float angleToPlater;
    private float maxSpeed;


    public Arrow(float positionX, float positionY, float radius, float speed, Drawable sprite, Player player) {
        super(positionX, positionY, radius, sprite);
        this.maxSpeed = speed;
        this.player = player;

        float distanceToPlayer = GameEntity.getDistanceBetweenObjects(this, player);
        float distanceToPlayerX = player.getPositionX() - positionX;
        float distanceToPlayerY = player.getPositionY() - positionY;

        float directionX = distanceToPlayerX/distanceToPlayer;
        float directionY = distanceToPlayerY/distanceToPlayer;


        // this.sprite.
        velocityX = directionX*maxSpeed;
        velocityY = directionY*maxSpeed;

        angleToPlater = 90-getAngleToPlayer();
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.save();
        canvas.rotate(angleToPlater, positionX, positionY);
        this.sprite.setBounds((int)(positionX-radius*2-20), (int)(positionY-radius), (int)(positionX+radius), (int)(positionY+radius));
        sprite.draw(canvas);
        canvas.restore();
    }

    @Override
    public void update(){
        float distanceToPlayer = GameEntity.getDistanceBetweenObjects(this, player);
        if (distanceToPlayer < player.getRadius() + radius || this.player.getShield().getCollisionWith(this)){
            if (distanceToPlayer < player.getRadius() + radius){
                player.loseVida();
            }
            Game.removeElement(this);
            return;
        }

        positionX += velocityX;
        positionY += velocityY;
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

    public float getAngleToPlayer(){
        float distanceToPlayer = GameEntity.getDistanceBetweenObjects(this, player);

        float distanceToPlayerX = player.getPositionX() - positionX;
        float distanceToPlayerY = player.getPositionY() - positionY;

        float directionX = distanceToPlayerX/distanceToPlayer;
        float directionY = distanceToPlayerY/distanceToPlayer;

        return (float) ( Math.atan2(directionX, directionY) * 180 / Math.PI);
    }
}
