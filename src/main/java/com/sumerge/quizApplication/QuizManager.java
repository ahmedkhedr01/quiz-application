package com.sumerge.quizApplication;

import java.util.HashMap;
import java.util.Map;

public class QuizManager {
    private Map<String, Quiz> quizzes;

    public QuizManager() {
        quizzes = new HashMap<>();
    }

    public void addQuiz(Quiz quiz) {
        quizzes.put(quiz.getTitle(), quiz);
    }

    public Quiz getQuiz(String title) {
        return quizzes.get(title);
    }

    public Map<String, Quiz> getAllQuizzes() {
        return quizzes;
    }

    public String getAllQuizzesTitles () {
        StringBuilder allQuizzesTitles = new StringBuilder();
        for (String title : quizzes.keySet()) {
            allQuizzesTitles.append(title).append("\n");
        }
        return allQuizzesTitles.toString();
    }
}
