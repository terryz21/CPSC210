package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * A True/False question.
 */
public class TrueFalseQuestion extends ShortAnswerQuestion {

    // REQUIRES: maxMark must be >= 0
    // EFFECTS: constructs true/false question with given maximum mark, question statement
    // and correct answer
    public TrueFalseQuestion(int maxMark, String questionString,
                             boolean correctAnswer) {
        super(maxMark, questionString, Boolean.toString(correctAnswer));
    }

    @Override
    public String getQuestionString() {
        return "'true' or 'false': " + super.getQuestionString();
    }
}
