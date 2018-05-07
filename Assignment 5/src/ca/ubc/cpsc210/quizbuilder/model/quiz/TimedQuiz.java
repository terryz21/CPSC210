package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.TimeIsUpException;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that enforces a time constraint -- the quiz is initializes
 * with the number of seconds a user is allowed for the quiz. The quiz ends if
 * the user takes longer than this time to complete the quiz.
 */
public class TimedQuiz extends Quiz {

    private long quizStartTime;
    private double numSeconds;

    // EFFECTS: construct quiz with given list of questions and timed to last
    // a maximum of numSeconds seconds.
    public TimedQuiz(QuestionsList questions, double numSeconds) {
        super(questions);
        this.numSeconds = numSeconds;
    }

    // MODIFIES: this
    // EFFECTS: starts the quiz and sets time that quiz starts
    public void startQuiz() {
        super.startQuiz();
        quizStartTime = System.nanoTime();
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string
    // that specifies time remaining;
    // throws TimeIsUpException if user has run out of time.
    @Override
    public String submitAnswer(String answer) throws TimeIsUpException {
        long curTime = System.nanoTime();
        double elapsedSeconds = (curTime - quizStartTime) / 1000000000.0;
        if (elapsedSeconds > numSeconds) {
            throw new TimeIsUpException("You have spent more than "
                    + numSeconds
                    + " seconds on the quiz. Your time is up, pencils down!"
                    + "\nYour final mark is: " + this.getMarkSoFar());
        }
        // Otherwise, report the amount of time left.
        super.checkAnswer(answer);
        double secsLeft = numSeconds - elapsedSeconds;
        return "(" + secsLeft + " seconds left to quiz end)";
    }
}
