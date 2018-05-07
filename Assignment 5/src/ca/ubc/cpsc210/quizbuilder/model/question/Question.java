package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * Represents a quiz question. A question is composed of a mark, and a question.
 */
public abstract class Question {

    // The maximum mark that a user can get for a right answer to this question.
    protected double maxMark;

    // The question string for the question.
    protected String questionString;

    //  REQUIRES: maxMark must be >=0
    //  EFFECTS: constructs a question with given maximum mark and question statement
    public Question(double maxMark, String questionString) {
        assert (maxMark > 0);

        this.maxMark = maxMark;
        this.questionString = questionString;
    }

    public double getMaxMark() {
        return maxMark;
    }

    // REQUIRES: newMark >= 0
    // EFFECTS: max mark available for this question is set to newMark
    public void setMaxMark(double newMark) {
        this.maxMark = newMark;
    }

    // EFFECTS: returns question string along with max mark that user can get for a right
    // answer to this question
    public String getQuestionString() {
        return questionString + " [" + maxMark + " points]";
    }

    // EFFECTS: returns true if answer is the correct answer to this question,
    // false otherwise
    public abstract boolean isCorrect(String answer);

}
