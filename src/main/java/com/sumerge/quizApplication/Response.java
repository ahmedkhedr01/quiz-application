package com.sumerge.quizApplication;

public class Response {
    private String responseText;
    private boolean isCorrect;

    public Response(String responseText, boolean isCorrect) {
        this.responseText = responseText;
        this.isCorrect = isCorrect;
    }

    public String getResponse() {
        return responseText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setResponse(String responseText) {
        this.responseText = responseText;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
