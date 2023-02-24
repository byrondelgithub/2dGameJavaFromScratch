package com.byron.twodgamejavafromscratch.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class JoystickShield extends GameEntity{

    private Paint outerCirclePaint;
    private Paint outerCirclePaintLine;
    private Paint innerCirclePaint;


    private float outerCircleRadius;
    private float innerCircleRadius;

    private float innerCirclePositionX;
    private float innerCirclePositionY;

    public boolean isTouched;
    private boolean isActive = false;

    private float actuatorX;
    private float actuatorY;

    private SurfaceView sv;


    public JoystickShield(SurfaceView sv, float centerPositionX, float centerPostitionY, float outerCircleRadius, float innerCircleRadius){
        super(centerPositionX, centerPostitionY);

        this.sv = sv;

        innerCirclePositionX = centerPositionX;
        innerCirclePositionY = centerPostitionY;


        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.RED);

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.RED);
        outerCirclePaint.setStyle(Paint.Style.FILL);
        outerCirclePaint.setAlpha(100);

        outerCirclePaintLine = new Paint();
        outerCirclePaintLine.setColor(Color.RED);
        outerCirclePaintLine.setStyle(Paint.Style.STROKE);
        outerCirclePaintLine.setStrokeWidth(10);
    }

    public void setPosition(float x, float y) {
        this.positionX = x;
        this.positionY = y;
        this.innerCirclePositionX = x;
        this.innerCirclePositionY = y;
    }

    public float getActuatorX() {
        return actuatorX;
    }

    public void setActuatorX(float actuatorX) {
        this.actuatorX = actuatorX;
    }

    public float getActuatorY() {
        return actuatorY;
    }

    public void setActuatorY(float actuatorY) {
        this.actuatorY = actuatorY;
    }

    @Override
    public void draw(Canvas canvas) {
        if (isActive){
            canvas.drawCircle(positionX, positionY, outerCircleRadius, outerCirclePaintLine);
            canvas.drawCircle(positionX, positionY, outerCircleRadius, outerCirclePaint);
            canvas.drawCircle(innerCirclePositionX, innerCirclePositionY, innerCircleRadius, innerCirclePaint);
        }
    }

    @Override
    public void update() {
        innerCirclePositionX = (int) (positionX + actuatorX*outerCircleRadius);
        innerCirclePositionY = (int) (positionY + actuatorY*outerCircleRadius);
    }

    @Override
    public boolean isPressed(float x, float y) {
        float touchDistance = getDistance(x, y);
        if (touchDistance < outerCircleRadius){
            isTouched = true;
            return  true;
        }
        isTouched = false;
        return false;
    }

    @Override
    public void setActuator(float x, float y) {
        float deltaX = x - positionX;
        float deltaY = y - positionY;

        float touchDistance = getDistance(x, y);

        if (touchDistance < outerCircleRadius){
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        }else{
            actuatorX = deltaX/touchDistance;
            actuatorY = deltaY/touchDistance;
        }
    }

    @Override
    public void reset() {
        isTouched = false;
        actuatorX = 0;
        actuatorY = 0;
    }

    @Override
    public void touchDown(float x, float y) {
        if (x > sv.getWidth()/2){
            isActive = true;
            setPosition(x, y);
            isPressed(x, y);
        }
    }

    @Override
    public void touchMove(float x, float y) {
        if (isTouched){
            setActuator(x, y);
        }
    }

    @Override
    public void touchUp(float x, float y) {
        isActive = false;
        reset();
    }

    private float getDistance(float x, float y){
        float deltaX = x - positionX;
        float deltaY = y - positionY;
        return (float)Math.sqrt(
                Math.pow((double) (deltaX), 2) +
                        Math.pow((double) (deltaY), 2)
        );
    }

    public float getAngle(){
        return -(float) ( Math.atan2(actuatorX, actuatorY) * 180 / Math.PI) ;
    }

}
