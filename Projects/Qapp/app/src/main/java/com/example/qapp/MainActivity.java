package com.example.qapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    final String SCORE_KEY = "SCORE";
    final String INDEX_KEY = "INDEX";
    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex = 0;
    private int mUserScore = 0;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;

    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),
    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(
                getApplicationContext(),
                "onCreate method is called",
                Toast.LENGTH_SHORT
        ).show();
        if (savedInstanceState != null){
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
        } else {
            mUserScore = 0;
            mQuestionIndex = 0;
        }
        mTxtQuestion = findViewById(R.id.textQuestion);
        QuizModel q = questionCollection[mQuestionIndex];
        mQuizQuestion = q.getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.textQuizStat);
        mQuizStatsTextView.setText(mUserScore+"");
        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);
        View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUsersAnswer(true);
                changeQuestionOnButtonClick();
            }
        };
        btnTrue.setOnClickListener(myClickListener);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUsersAnswer(false);
                changeQuestionOnButtonClick();
            }
        });

    }

    private void changeQuestionOnButtonClick(){
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore + "");
    }
    private void evaluateUsersAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(), R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
            mUserScore++;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }
    }
    protected void onStart(){
        super.onStart();
        Toast.makeText(
                getApplicationContext(),
                "onStart method is called",
                Toast.LENGTH_SHORT
        ).show();
    }
    protected void onResume(){
        super.onResume();
        Toast.makeText(
                getApplicationContext(),
                "onResume method is called",
                Toast.LENGTH_SHORT
        ).show();
    }
    protected void onPause(){
        super.onPause();
        Toast.makeText(
                getApplicationContext(),
                "onPause method is called",
                Toast.LENGTH_SHORT
        ).show();
    }
    protected void onStop(){
        super.onStop();
        Toast.makeText(
                getApplicationContext(),
                "onStop method is called",
                Toast.LENGTH_SHORT
        ).show();
    }
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(
                getApplicationContext(),
                "onDestroy method is called",
                Toast.LENGTH_SHORT
        ).show();
    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
        outState.putInt(SCORE_KEY, mUserScore);
    }
}