package com.example.fingerspeedgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView thousandTextView;
    private Button tapBtn;
    private CountDownTimer countDownTimer;
    private long initialCountDownInMillis = 30000;
    private int timerInterval = 1000;
    private int remainingTime = 60;
    private int aThousand = 100;

    private final String REMAINING_TIME_KEY = "remaining";
    private final String THOUSAND_KEY = "thousand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timeTxt);
        thousandTextView = findViewById(R.id.thousandTxt);
        tapBtn = findViewById(R.id.btnTap);

        thousandTextView.setText(aThousand + "");

        if (savedInstanceState != null){
            remainingTime = savedInstanceState.getInt(REMAINING_TIME_KEY);
            aThousand = savedInstanceState.getInt(THOUSAND_KEY);
            restoreGame();
        }
        tapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aThousand--;
                thousandTextView.setText(aThousand + "");
                if (remainingTime > 0 && aThousand <= 0){
                    Toast.makeText(
                            MainActivity.this,
                            "Congratulation",
                            Toast.LENGTH_SHORT);
                    showAlert("Congratulation!!", "please reset the game");
                }
            }
        });
        if (savedInstanceState == null) {
            countDownTimer = new CountDownTimer(initialCountDownInMillis, timerInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    remainingTime = (int) (millisUntilFinished / 1000);
                    timerTextView.setText(remainingTime + "");
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "countDown finished", Toast.LENGTH_SHORT);
                    if (remainingTime > 0 && aThousand <= 0) {
                        Toast.makeText(
                                MainActivity.this,
                                "Congratulation",
                                Toast.LENGTH_SHORT);
                        showAlert("Congratulation!!", "please reset the game");
                    } else {
                        showAlert("shitty attempt", "try again ");
                    }
                }
            };
            countDownTimer.start();
        }

    }
    private void resetTheGame(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        aThousand = 100;
        thousandTextView.setText(aThousand+"");
        timerTextView.setText(remainingTime+"");
        countDownTimer = new CountDownTimer(initialCountDownInMillis, timerInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int)(millisUntilFinished / 1000);
                timerTextView.setText(remainingTime+"");
            }

            @Override
            public void onFinish() {
                    showAlert("Finished", "try again ");
            }
        };
        countDownTimer.start();
    }
    private void showAlert(String title, String massage){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(massage)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetTheGame();
                    }
                }).show();
        alertDialog.setCancelable(false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(REMAINING_TIME_KEY, remainingTime);
        outState.putInt(THOUSAND_KEY, aThousand);
        countDownTimer.cancel();
    }
    private void restoreGame(){
        int restoreRemainingTime = remainingTime;
        int restoreAThousand = aThousand;
        timerTextView.setText(restoreRemainingTime+"");
        thousandTextView.setText(restoreAThousand+"");
        countDownTimer = new CountDownTimer((long)(remainingTime * 1000), timerInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int)(millisUntilFinished / 1000);
                timerTextView.setText(remainingTime+"");
            }

            @Override
            public void onFinish() {
                showAlert("Finished", "try again ");
            }
        };
        countDownTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.my_item){
            Log.i("FUCK", "YOU");
            Toast.makeText(
                    MainActivity.this,
                    "Hello I'm " + BuildConfig.VERSION_NAME,
                    Toast.LENGTH_SHORT);
        }
        return true;
    }
}