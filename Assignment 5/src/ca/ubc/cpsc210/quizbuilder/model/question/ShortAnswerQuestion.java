package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * A question that has a single, unambiguous, correct answer.
 */
public class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    // REQUIRES: maxMark must be >= 0
    // EFFECTS: constructs short answer question with given maximum mark, question statement
    // and correct answer
    public ShortAnswerQuestion(int maxMark, String questionString,
                               String correctAnswer) {
        super(maxMark, questionString);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {

        return answer.equals(correctAnswer);
    }

}
