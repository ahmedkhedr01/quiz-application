package com.sumerge.quizApplication;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        List<String> possibleAnswers = question.getPossibleAnswers();
        for (int i = 0; i < possibleAnswers.size(); i++) {
            System.out.println((i + 1) + ": " + possibleAnswers.get(i));
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
