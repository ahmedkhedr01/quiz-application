package com.sumerge.quizApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class QuizApplication {
    private User user;
    private QuizManager quizManager;
    private UserInterface userInterface;

    public QuizApplication() {
        quizManager = new QuizManager();
        userInterface = new UserInterface();
    }

    public void registerUser() {
        String name = userInterface.getUserInput("Enter your name: ");
        user = new User(name);
    }

    public void createQuiz() {
        String title = userInterface.getUserInput("Enter the title of the quiz: ");
        List<Question> questions = new ArrayList<>();
        while (true) {
            String questionText = userInterface.getUserInput("Enter a question (or type 'done' to finish): ");
            if (questionText.equalsIgnoreCase("done")) break;
            List<String> possibleAnswers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                possibleAnswers.add(userInterface.getUserInput("Enter possible answer " + (i + 1) + ": "));
            }

            int correctAnswerIndex = -1;
            while(correctAnswerIndex < 1 || correctAnswerIndex > 4) {
                try {
                    correctAnswerIndex = Integer.parseInt(userInterface.getUserInput("Enter the index of the correct answer (1 to 4): "));
                    if (correctAnswerIndex < 1 || correctAnswerIndex > 4) {
                        userInterface.displayMessage("Invalid input. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    userInterface.displayMessage("Invalid input. Please enter a number between 1 and 4.");
                }
            }
            questions.add(new Question(questionText, possibleAnswers, correctAnswerIndex));
        }

        quizManager.addQuiz(new Quiz(title, questions));
    }

    public void startQuiz() {
        if(quizManager.getAllQuizzes().isEmpty()) {
            userInterface.displayMessage("No quizzes available.");
            return;
        }

        String quizTitle = userInterface.getUserInput("Enter the title of the quiz you want to take: \n" + quizManager.getAllQuizzesTitles());

        if(quizTitle.isEmpty()) {
            userInterface.displayMessage("Invalid input.");
            return;
        }

        Quiz quiz = quizManager.getQuiz(quizTitle);

        if (quiz == null) {
            userInterface.displayMessage("Quiz not found.");
            return;
        }

        List<Question> randomizedQuestions = new ArrayList<>(quiz.getQuestions());
        Collections.shuffle(randomizedQuestions);

        List<Response> responses = new ArrayList<>();
        userInterface.displayMessage("Starting the quiz...");
        for (Question question : randomizedQuestions) {
            userInterface.displayQuestion(question);

            int userAnswerIndex = -1;
            while (userAnswerIndex < 1 || userAnswerIndex > 4) {
                try {
                    userAnswerIndex = Integer.parseInt(userInterface.getUserInput("Your answer (type index 1 to 4): ")) ;
                    if (userAnswerIndex < 1 || userAnswerIndex > 4) {
                        userInterface.displayMessage("Invalid input. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    userInterface.displayMessage("Invalid input. Please enter a number between 1 and 4.");
                }
            }
            boolean isCorrect = question.isCorrectAnswer(userAnswerIndex);
            userInterface.displayMessage(isCorrect ? "Correct!" : "Incorrect! The correct answer is: " + question.getCorrectAnswerIndex());
            responses.add(new Response(question.getQuestionText(), isCorrect));
        }

        user.addResponseToAQuiz(quiz.getTitle(), responses);
        userInterface.displayMessage("Quiz finished! Your score: " + quiz.calculateScore(responses));
    }

    public void showScores() {
        userInterface.displayMessage(user.getName() + "'s Scores:");
        for (String quizTitle : user.getResponsesMap().keySet()) {
            Quiz quiz = quizManager.getQuiz(quizTitle);
            int score = quiz.calculateScore(user.getResponsesToQuiz(quizTitle));
            userInterface.displayMessage("Quiz: " + quizTitle + " - Score: " + score);
        }
    }

    public void run() {
        registerUser();
        while (true) {
            String command = userInterface.getUserInput("Enter command (-c to create a quiz, -s to start a quiz, -v to view scores, -q to quit): ");
            switch (command) {
                case "-c":
                    createQuiz();
                    break;
                case "-s":
                    startQuiz();
                    break;
                case "-v":
                    showScores();
                    break;
                case "-q":
                    userInterface.displayMessage("Exiting...");
                    return;
                default:
                    userInterface.displayMessage("Invalid command.");
            }
        }
    }

    public static void main(String[] args) {
        QuizApplication app = new QuizApplication();
        app.run();
    }
}
