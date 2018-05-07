package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * Created by terryzhang on 2017-08-01.
 */
public class MultiplicationQuestion extends Question {

    private int correctAnswer;

    // EFFECTS: constructs multiplication question with given maximum mark and two numbers
    //          that are to be multiplied
    public MultiplicationQuestion(double maxMark, int factor1, int factor2) {
        super(maxMark, (Integer.toString(factor1) + "+" + Integer.toString(factor2) +" = ???"));

        this.correctAnswer = factor1 * factor2;
    }

    @Override
    public boolean isCorrect(String answer) {
        try {
            int givenAnswer = Integer.parseInt(answer);

            return (givenAnswer == this.correctAnswer);

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
