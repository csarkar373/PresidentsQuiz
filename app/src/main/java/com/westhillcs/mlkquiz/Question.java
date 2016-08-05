package com.westhillcs.mlkquiz;

/**
 * Created by Chandan on 5/1/2016.
 */
public class Question {

    private int imageReference;
    private String questionBody;

    private String choiceA;
    private String choiceB;
    private String choiceC;

    private String correctChoice; // should be one letter String
    private boolean creditGiven;  // starts off false; switched to true once correctly answered

    public void giveCredit() {
        this.creditGiven = true;
    }

    public boolean isCreditGiven() {
        return creditGiven;
    }

    public int getImage() {
        return imageReference;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getAnswer() {
        return correctChoice;
    }

    public String getQuestionBody() {
        return questionBody;
    }



    public Question(int imageReference, String questionBody, String choiceA, String choiceB, String choiceC, String correctChoice) {
        this.imageReference = imageReference;
        this.questionBody = questionBody;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.correctChoice = correctChoice;
        // question has not been answered correctly yet
        this.creditGiven = false;
    }
}
