package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exam {

    private List<Question> questions;
    private int timer; // in minutes

    // Constructor
    public Exam(List<Question> questions, int timer) {
        this.questions = questions;;
        this.timer = timer;
    }

    // Getter methods

    public List<Question> getQuestions() {
        return  questions;
    }

    public int getTimer() {
        return timer;
    }

    public static Exam createSampleExam() {

        List<Question> sampleQuestions = new ArrayList<>();
        sampleQuestions.add(new Question("What is the capital of France?", List.of("A. London", "B. Paris", "C. Berlin", "D. Madrid"), 1));
        sampleQuestions.add(new Question("Which programming language is used to develop Android apps?", List.of("A. Java", "B. C++", "C. Python", "D. Swift"), 0));
        sampleQuestions.add(new Question("What does JVM stand for in Java?", List.of("A. Java Virtual Machine", "B. Java Visual Machine", "C. Java Virtual Memory", "D. Java Virtual Model"), 0));
        sampleQuestions.add(new Question("Which language is not statically typed?", List.of("A. Java", "B. C++", "C. Python", "D. TypeScript"), 2));
        sampleQuestions.add(new Question("What is the main purpose of a constructor in Java?", List.of("A. To create objects", "B. To initialize variables", "C. To destroy objects", "D. To define methods"), 1));
        sampleQuestions.add(new Question("Which of the following is a functional programming language?", List.of("A. Java", "B. C#", "C. Haskell", "D. C++"), 2));

        return new Exam(sampleQuestions, 1); // 1-minute timer for the sample exam
    }
}
