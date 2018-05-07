package ca.ubc.cpsc210.quizbuilder.model.exceptions;

/**
 * Indicates that the user is out of tries.
 */
public class OutOfTriesException extends Exception {
    public OutOfTriesException(String msg) {
        super(msg);
    }
}
