package com.sumerge.quizApplication;

import java.util.List;

public class Quiz {
    String title;
    List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void displayQuestions () {
        StringBuilder sb = new StringBuilder();

        questions.forEach(question -> {
            sb.append(question.getQuestionText());
            sb.append("\n");
            question.getPossibleAnswers().forEach(answer -> {
                sb.append(answer);
                sb.append("\n");
            });
        });

        System.out.println(sb.toString());
    }

    public int calculateScore(List<Response> responseList) {
       int score = 0;

       for (Response response : responseList) {
           if (response.isCorrect()) {
               score++;
           }
       }

         return score;

    }
}
