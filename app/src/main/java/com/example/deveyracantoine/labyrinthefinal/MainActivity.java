package com.example.deveyracantoine.labyrinthefinal;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int countSec = 30;
    private CountDownTimer countDown;
    private boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCountDown(30000);
    }

    private void update() {
        countSec--;
        ((TextView) findViewById(R.id.textResult)).setText("" + countSec);
    }

    public void restart(View view){
        MapView mapView = (MapView) findViewById(R.id.view);
        mapView.init();
        isGameActive = true;
        onStop();
        countSec = 30;
        startCountDown(30000);
        hideRestart();
    }

    public void moveRight(View view) {
        if(isGameActive){
            MapView mapView = (MapView) findViewById(R.id.view);
            mapView.moveRight();
            checkVictory();
        }else{
            return;
        }
    }

    public void moveLeft(View view) {
        if(isGameActive){
            MapView mapView = (MapView) findViewById(R.id.view);
            mapView.moveLeft();
            checkVictory();
        }else{
            return;
        }
    }

    public void moveTop(View view) {
       if(isGameActive){
           MapView mapView = (MapView) findViewById(R.id.view);
           mapView.moveTop();
           checkVictory();
       }else{
           return;
       }
    }

    public void moveBot(View view) {
        if(isGameActive){
            MapView mapView = (MapView) findViewById(R.id.view);
            mapView.moveBot();
            checkVictory();
        }else{
            return;
        }

    }

    private void checkVictory() {
        MapView mapView = (MapView) findViewById(R.id.view);
        if (mapView.isVictory() == true) {
            isGameActive = false;
            TextView mTextView = (TextView) findViewById(R.id.textResult);
            mTextView.setText("Victory !");
            showRestart();
            onStop();
        }
    }

    public void startCountDown(long duration) {
        if (countDown != null) countDown.cancel();
        countDown = new CountDownTimer(duration, 1000) {

            public void onTick(long millisUntilFinished) {
                update();
            }

            public void onFinish() {
                ((TextView) findViewById(R.id.textResult)).setText("Perdu !");
                countDown = null;
                isGameActive = false;
                showRestart();
            }
        }.start();
    }

    protected void onStop() { //si l'activité passe en pause ...
        super.onStop();

        //super.onPause();
        if (countDown != null) countDown.cancel(); // arrêt du timer
        countDown = null;
    }

    private void showRestart(){
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setVisibility(View.VISIBLE);
    }

    private void hideRestart(){
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setVisibility(View.GONE);
    }

}
