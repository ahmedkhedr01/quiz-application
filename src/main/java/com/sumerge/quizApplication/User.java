package com.sumerge.quizApplication;

import java.util.HashMap;
import java.util.List;

public class User {
    private String name;
    private HashMap<String,List<Response>> responsesMap;

    public User(String name) {
        this.name = name;
        responsesMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, List<Response>> getResponsesMap() {
        return responsesMap;
    }

    public void setResponsesMap(HashMap<String, List<Response>> responsesToAQuiz) {
        this.responsesMap = responsesToAQuiz;
    }

    public void addResponseToAQuiz(String quizName, List<Response> responses) {
        responsesMap.put(quizName, responses);
    }

    public List<Response> getResponsesToQuiz(String quizName) {
        return responsesMap.get(quizName);
    }

}
