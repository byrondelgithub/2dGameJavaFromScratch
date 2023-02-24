package com.byron.twodgamejavafromscratch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pantala completa
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Set the view to our game
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onPause() {
        game.pause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}