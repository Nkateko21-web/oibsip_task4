package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OnlineExamSystem {

    private static User currentUser;
    private static Exam currentExam;
    private static int currentQuestionIndex = 0;

    public static void main(String[] args) {

        initializeSampleData();
        // Starting the exam
        startExam();

    }

    public static void initializeSampleData() {

        //Sample exam with a timer of 5 minutes
        currentExam = Exam.createSampleExam();
        // Sample user
        currentUser = new User("joe_doe", BCrypt.hashpw("password", BCrypt.gensalt()), "John Doe", "joe.doe@example.com");
    }

    public static void startExam() {

        Scanner scanner = new Scanner(System.in);

        login();
        updateProfileAndPassword();

        // Timer and auto-submit (simulate by waiting for the timer duration)


        long startTime = System.currentTimeMillis();
        long endTime = startTime + currentExam.getTimer() * 60 * 1000; // convert minutes to milliseconds
        System.out.println("\nTimer started for " + currentExam.getTimer() + " minutes...");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Exam start time: " + dateFormat.format(new Date(startTime)));
        System.out.println("Exam end time: " + dateFormat.format(new Date(endTime)));

        // Display questions and allow the user to select answers
        while (currentQuestionIndex < currentExam.getQuestions().size() && System.currentTimeMillis() < endTime) {
            long currentTime = System.currentTimeMillis();
            System.out.println("Time remaining: " + formatTimeRemaining(endTime - currentTime));

            Question currentQuestion = getCurrentQuestion();
            displayQuestion(currentQuestion);
            // Move to the next question
            currentQuestionIndex++;
            
            selectAnswer(scanner, currentQuestion);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // closing session and logout
        logout();
    }

    private static String formatTimeRemaining(long TimeRemaining) {
        long minutes = (TimeRemaining / (1000 * 60)) % 60;
        long seconds = (TimeRemaining / 1000) % 60;
        return String.format("%d:%02d", minutes, seconds);
    }


    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // validate login credentials
        if (currentUser.getUsername().equals(username) && BCrypt.checkpw(password, currentUser.getPassword())) {
            System.out.println("Login successful. Welcome!");
        } else {
            System.out.println("Invalid credentials. Exiting...");
            System.exit(0);
        }
    }

    public static void updateProfileAndPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update your profile: ");
        System.out.println("Enter your full name: ");
        String newFullName = scanner.nextLine();
        currentUser.setFullName(newFullName);

        System.out.println("Enter your email: ");
        String newEmail = scanner.nextLine();
        currentUser.setEmail(newEmail);

        System.out.println("Change your password: ");
        System.out.println("Enter your current password: ");
        String currentPaasword = scanner.nextLine();

        // Validate current password
        if (BCrypt.checkpw(currentPaasword, currentUser.getPassword())) {
            System.out.println("Enter your new password: ");
            String newPassword = scanner.nextLine();
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            currentUser.setPassword(hashedPassword);
            System.out.println("Profile and password updated successfully. " + currentUser.getFullName() + "!");
        } else {
            System.out.println("Incorrect current password. Exiting...");
            System.exit(0);
        }
    }

    public static void displayQuestion(Question question) {
        System.out.println("\n" + question.getQuestionText());
        for (int i = 0; i < question.getOptions().size(); i++) {
            System.out.println(question.getOptions().get(i));
        }
    }

    public static void selectAnswer(Scanner scanner, Question currentQuestion) {
        System.out.println("Select your answer (A, B, C, D): ");
        String selectedOption = scanner.nextLine().toUpperCase();

        if (isValidOption(selectedOption)) {
            currentQuestion.setSelectedAnswer(selectedOption);
            System.out.println("Answer record: " + selectedOption);
        } else {
            System.out.println("Invalid option. Please select (A, B, C, D)");
        }
    }

    private static boolean isValidOption(String option) {
        return option.equals("A") || option.equals("B") || option.equals("C") || option.equals("D");
    }

    private static Question getCurrentQuestion() {

        if (currentQuestionIndex >= 0 && currentQuestionIndex < currentExam.getQuestions().size()) {
            return currentExam.getQuestions().get(currentQuestionIndex);
        } else {
            return null;
        }

    }

    public static void logout() {
        System.out.println("Session closed. Logging out, Goodbye " + currentUser.getFullName() + "!");
        System.exit(0);
    }
}
