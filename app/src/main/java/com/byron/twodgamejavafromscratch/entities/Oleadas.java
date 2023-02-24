package com.byron.twodgamejavafromscratch.entities;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import com.byron.twodgamejavafromscratch.Game;
import com.byron.twodgamejavafromscratch.GameLoop;
import com.byron.twodgamejavafromscratch.R;

public class Oleadas extends GameEntity {

    private int updateCount = 0;
    private int nivel = 0;
    private Resources res;
    private Player player;
    SurfaceView sv;

    public Oleadas(float positionX, float positionY, Resources res, Player player, SurfaceView sv) {
        super(positionX, positionY);
        this.res = res;
        this.player = player;
        this.sv = sv;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        canvas.drawText("Nivel: " + (nivel+1), canvas.getWidth()/2-100, (float) (canvas.getHeight()*0.1), paint);
    }

    @Override
    public void update() {
        updateCount+=1;
        float rNum = (int)(Math.random()*(70-nivel*5));
        if (rNum == 0){
            addArrow();
        }
        if (updateCount >= GameLoop.MAX_UPS*10){
            updateCount = 0;
            nivel+=1;
        }
    }

    private void addArrow(){
        float positionX = 0;
        float positionY = 0;

        int rNum = (int)(Math.random()*4);
        if (rNum == 0){
            positionX = -100;
            positionY = (int)(Math.random()*sv.getHeight());
        }else if(rNum==1){
            positionX = sv.getWidth()+100;
            positionY = (int)(Math.random()*sv.getHeight());
        }else if(rNum==2){
            positionX = (int)(Math.random()*sv.getWidth());;
            positionY = 0;
        }else if(rNum==3){
            positionX = (int)(Math.random()*sv.getWidth());
            positionY = sv.getHeight()+100;
        }


        float speed = (float) ((200+Math.random()*(nivel+1)*50) / GameLoop.MAX_UPS);

        Arrow arrow = new Arrow(positionX, positionY, 25F, speed, res.getDrawable(R.drawable.arrow), player);
        Game.addElement(arrow);
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
