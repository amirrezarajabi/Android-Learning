package com.example.qapp;

public class QuizModel {
    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int question, boolean answer){

        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        this.mQuestion = mQuestion;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        this.mAnswer = mAnswer;
    }
}
