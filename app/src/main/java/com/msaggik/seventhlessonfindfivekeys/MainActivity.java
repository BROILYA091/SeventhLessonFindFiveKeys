package com.msaggik.seventhlessonfindfivekeys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView screen, coordinates;
    private float x;
    private float y;
    private int[] treasureCoordinates;
    private int interval = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);
        coordinates = findViewById(R.id.coordinates);

        treasureCoordinates = new int[10];
        generateRandomCoordinates();

        screen.setOnTouchListener(listener);
    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    coordinates.setText("Нажатие " + x + ", " + y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    coordinates.setText("Движение " + x + ", " + y);
                    checkForTreasure();
                    break;
                case MotionEvent.ACTION_UP:
                    coordinates.setText("Отпускание " + x + ", " + y);
                    break;
            }

            return true;
        }
    };

    private void generateRandomCoordinates() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            treasureCoordinates[i] = random.nextInt(400) + 50;
        }
    }

    private void checkForTreasure() {
        for (int i = 0; i < 10; i += 2) {
            if (x >= (treasureCoordinates[i] - interval) && x <= (treasureCoordinates[i] + interval) &&
                    y >= (treasureCoordinates[i + 1] - interval) && y <= (treasureCoordinates[i + 1] + interval)) {
                int treasureNumber = i / 2 + 1;
                Toast.makeText(MainActivity.this, "Найден " + treasureNumber + " ключ", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}