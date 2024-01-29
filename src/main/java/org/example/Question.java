package org.example;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;
    private String selectedAnswer;

    // Constructor
    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    // Getter methods

    public String getQuestionText() {
        return  questionText;
    }
    public String getSelectedAnswer() {
        return selectedAnswer;
    }
    public List<String> getOptions() {
        return  options;
    }
    public int getCorrectOption() {
        return  correctOption;
    }

    // Setter methods
    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
