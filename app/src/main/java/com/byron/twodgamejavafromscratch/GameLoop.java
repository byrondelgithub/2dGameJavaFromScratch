package com.byron.twodgamejavafromscratch;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private static final double MAX_UPS = 60;
    private  static final double UPS_PERIOD = 1000/MAX_UPS;
    private Game game;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning = false;
    private double averageUPS;
    private double averageFPS;

    private int updateCount = 0;
    private int frameCount = 0;

    private long startTime = 0;
    private long elapsedTime = 0;
    private long sleepTime = 0;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        this.isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning){
            try {
                canvas = surfaceHolder.lockCanvas();
                this.drawInCanvas(canvas);
            } catch (IllegalArgumentException e){
                    e.printStackTrace();
            } finally {
                if (canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }



            this.pauseLoop();
            this.skipFrames();

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000){
                averageUPS = updateCount;
                averageFPS = frameCount;
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }


    private void drawInCanvas(Canvas canvas) {
        synchronized (surfaceHolder) {
            game.update();
            updateCount++;
            game.draw(canvas);
        }
    }

    private void pauseLoop(){
        elapsedTime = System.currentTimeMillis() - startTime;
        sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
        if (sleepTime > 0){
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void skipFrames() {
        while(sleepTime < 0 && updateCount < MAX_UPS-1){
            game.update();
            updateCount++;
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
        }
    }

}
