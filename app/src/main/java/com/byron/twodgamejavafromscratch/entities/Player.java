package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.byron.twodgamejavafromscratch.GameLoop;
import com.byron.twodgamejavafromscratch.entities.geometry.Circle;

public class Player extends Circle {
    private JoystickShield joystick;
    private JoystickMovement joystickMovement;
    private Shield shield;
    private Ring ring;
    private int vidas = 10;
    private float maxSpeed = (float) (350 / GameLoop.MAX_UPS);
    private boolean center = true;

    public Player(float positionX, float positionY, float radius, Drawable sprite, JoystickShield joystick, JoystickMovement joystickMovement, Ring ring){
        super(positionX, positionY, radius, sprite);
        this.radius = radius;

        this.joystick = joystick;
        this.joystickMovement = joystickMovement;
        this.shield = new Shield(positionX, positionY, radius+150, -135, 90);
        this.ring = ring;
    }

    @Override
    public void draw(Canvas canvas) {
        if (center){
            centerPlayer(canvas);
            center = false;
        }
        super.draw(canvas);
        shield.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Vidas: " + vidas, canvas.getWidth()/2-100, (float) (canvas.getHeight()*0.9), paint);

        if (vidas <= 0){
            paint.setColor(Color.RED);
            paint.setTextSize(200);
            canvas.drawText("GAMEOVER!", canvas.getWidth()/2-600, (float) (canvas.getHeight()*0.5), paint);
        }
    }

    public void centerPlayer(Canvas canvas){
        this.positionX = canvas.getWidth() / 2;
        this.positionY = canvas.getHeight() / 2;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    @Override
    public void update() {
        velocityX = joystickMovement.getActuatorX()*maxSpeed;
        velocityY = joystickMovement.getActuatorY()*maxSpeed;
        positionX += velocityX;
        positionY += velocityY;
        if (!ring.getCollideWith((float) (positionX-radius), (float) (positionY+radius), (float) (positionX+radius), (float) (positionY-radius))){
            positionX -= velocityX;
            positionY -= velocityY;
        }

        float angle = 0;
        if (joystick.isTouched && joystick.getAngle() != 0){
            angle = joystick.getAngle();
        }else{
            angle = shield.getAngle();
        }
        shield.setPosition(this.positionX, this.positionY, angle);
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

    public Shield getShield() {
        return shield;
    }

    public void loseVida(){
        vidas -= 1;
    }
}
