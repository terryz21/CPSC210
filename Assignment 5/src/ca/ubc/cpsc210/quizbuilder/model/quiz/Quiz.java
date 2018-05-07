package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.TimeIsUpException;
import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represent a quiz that has a list of questions and keeps track of an ongoing
 * quiz --- mark so far, the next question to show to the user, etc.
 */
public abstract class Quiz {
    // The list of questions in this quiz
    protected QuestionsList questions;

    // The index of the next question to show to the user.
    private int nextQuestion;

    // Whether or not this quiz has started.
    private boolean quizStarted;

    // Whether or not this quiz has already been taken.
    private boolean quizFinished;

    // The user's mark so far.
    private int markSoFar;

    // The current question that is displayed to the user.
    protected Question curQuestion;

    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public Quiz(QuestionsList questions) {
        assert questions.length() > 0;
        // Set the fields to sensible initial values.
        this.questions = questions;
        this.markSoFar = 0;
        this.quizStarted = false;
        this.quizFinished = false;
        this.nextQuestion = 0;
        this.curQuestion = null;
    }

    // REQUIRES: quiz must be ongoing or finished
    // EFFECTS: returns mark earned so far
    public int getMarkSoFar() {
        assert (this.quizStarted || this.quizFinished);

        return markSoFar;
    }

    public int getMaxMark() {
        return questions.getMaxMark();
    }

    // MODIFIES: this
    // EFFECTS: starts the quiz.
    public void startQuiz() {
        assert (!this.quizStarted);
        assert (!this.quizFinished);

        this.quizStarted = true;
    }

    // REQUIRES: curQuestion must not be the last question
    // MODIFIES: this
    // EFFECTS: returns next question in quiz
    public Question getNextQuestion() {
        assert (nextQuestion < this.questions.length());

        Question ret = questions.getQuestion(nextQuestion);
        this.nextQuestion += 1;
        curQuestion = ret;
        return ret;
    }

    // EFFECTS: returns whether or not curQuestion is the last question.
    public boolean anymoreQuestions() {
        return (nextQuestion < this.questions.length());
    }

    // MODIFIES: this
    // EFFECTS: Ends the quiz. Returns a string to tell user their final mark.
    public String endQuiz() {
        assert (this.quizStarted);
        assert (!this.quizFinished);

        this.quizStarted = false;
        this.quizFinished = true;
        return "Your final mark is: " + markSoFar;
    }

    // MODIFIES: this
    // EFFECTS: checks the answer to the current question, updates marks earned and
    // returns true if the answer is correct, and false otherwise.
    public boolean checkAnswer(String answer) {
        if (this.curQuestion.isCorrect(answer)) {
            markSoFar += curQuestion.getMaxMark();
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // throws RetryAnswerException if the user should re-try the question;
    // throws TimeIsUpException if user has run out of time.
    // throws OutOfTriesException if user has used up all attempts to answer the question.
    public abstract String submitAnswer(String answer)
            throws RetryAnswerException, TimeIsUpException, OutOfTriesException;

}
