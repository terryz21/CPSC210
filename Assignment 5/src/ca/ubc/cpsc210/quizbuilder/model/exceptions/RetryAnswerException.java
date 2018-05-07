package ca.ubc.cpsc210.quizbuilder.model.exceptions;

/**
 * Indicates that the user should try answering the question again.
 * Nothing to change here for the assignment.
 */
public class RetryAnswerException extends Exception {

    private static final long serialVersionUID = 1L;

    public RetryAnswerException(String msg) {
        super(msg);
    }

}
