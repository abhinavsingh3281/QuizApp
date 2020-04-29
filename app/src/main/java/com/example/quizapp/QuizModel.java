package com.example.quizapp;

public class QuizModel {
    private int mQuestion;
    private Boolean mAnswer;
    //m here stands for member of main class


    public QuizModel(int question, Boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public Boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Boolean answer) {
        mAnswer = answer;
    }
}
