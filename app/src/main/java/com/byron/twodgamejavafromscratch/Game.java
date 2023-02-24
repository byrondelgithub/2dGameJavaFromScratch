package com.byron.twodgamejavafromscratch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.byron.twodgamejavafromscratch.entities.GameEntity;
import com.byron.twodgamejavafromscratch.entities.JoystickMovement;
import com.byron.twodgamejavafromscratch.entities.JoystickShield;
import com.byron.twodgamejavafromscratch.entities.Oleadas;
import com.byron.twodgamejavafromscratch.entities.Player;
import com.byron.twodgamejavafromscratch.entities.Ring;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private static ArrayList<GameEntity> elements = new ArrayList<>();
    private static ArrayList<GameEntity> elementsToRemove = new ArrayList<>();
    private static ArrayList<GameEntity> elementsToAdd = new ArrayList<>();
    private Player player;
    private Bitmap background;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        JoystickShield joystick = new JoystickShield(this,275, 350, 200, 100);
        JoystickMovement joystickM = new JoystickMovement(this,275, 350, 200, 100);
        Ring ring = new Ring(0, 0, 0, 0);
        this.player = new Player(0, 0, 25, getResources().getDrawable(R.drawable.player), joystick, joystickM, ring);
        Oleadas oleadas = new Oleadas(0, 0, getResources(), player, this);


        this.elements.add(player);
        this.elements.add(joystick);
        this.elements.add(joystickM);
        this.elements.add(ring);
        this.elements.add(oleadas);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (player.getVidas() <= 0){
                    player.setVidas(10);
                }
                synchronized (elements){
                    for (GameEntity e:
                            elements) {
                        e.touchDown(event.getX(), event.getY());
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                synchronized (elements){
                    for (GameEntity e:
                            elements) {
                        e.touchMove(event.getX(), event.getY());
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                synchronized (elements){
                    for (GameEntity e:
                            elements) {
                        e.touchUp(event.getX(), event.getY());
                    }
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(background, null, new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        drawUPS(canvas);
        drawFPS(canvas);

        synchronized (elements){
            for (GameEntity e:
                    elements) {
                e.draw(canvas);
            }
        }
    }

    public static void removeElement(GameEntity g){
        synchronized (elementsToRemove){
            elementsToRemove.add(g);
        }
    }

    public static void addElement(GameEntity g){
        synchronized (elementsToAdd){
            elementsToAdd.add(g);
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Average UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Average FPS: " + averageUPS, 100, 200, paint);
    }

    public void update() {
        synchronized (elements) {
            for (GameEntity g : elementsToRemove) {
                elements.remove(g);
            }
            elementsToRemove = new ArrayList<>();
        }
        synchronized (elements){
            for (GameEntity g: elementsToAdd){
                elements.add(g);
            }
            elementsToAdd = new ArrayList<>();
        }
        if (player.getVidas() <= 0){
            return;
        }
        synchronized (elements){
            for (GameEntity e:
                    elements) {
                e.update();
            }
        }
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
