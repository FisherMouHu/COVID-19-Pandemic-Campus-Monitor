package Model;

import java.sql.Timestamp;

public class Question {
    private String id;
    private String questionTitle;
    private String question;
    private String questionEmail;
    private String questionPeople;
    private String answer;
    private String answerEmail;
    private String answerPeople;
    private Timestamp questionTime;
    private Timestamp answerTime;

    public Question(String id, String questionTitle, String question, String questionEmail, String questionPeople, Timestamp questionTime) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.question = question;
        this.questionEmail = questionEmail;
        this.questionPeople = questionPeople;
        this.questionTime = questionTime;
    }

    public Question(String id, String questionTitle, String question, String questionEmail, String questionPeople, Timestamp questionTime, String answer, String answerEmail, String answerPeople, Timestamp answerTime) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.question = question;
        this.questionEmail = questionEmail;
        this.questionPeople = questionPeople;
        this.questionTime = questionTime;
        this.answer = answer;
        this.answerEmail = answerEmail;
        this.answerPeople = answerPeople;
        this.answerTime = answerTime;
    }

    public String getId() {
        return id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionEmail() {
        return questionEmail;
    }

    public String getQuestionPeople() {
        return questionPeople;
    }

    public Timestamp getQuestionTime() {
        return questionTime;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnswerEmail() {
        return answerEmail;
    }

    public String getAnswerPeople() {
        return answerPeople;
    }

    public Timestamp getAnswerTime() {
        return answerTime;
    }
}
