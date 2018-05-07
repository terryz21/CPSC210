package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that requires the right answer to a question before the
 * user can move on.
 */
public class EachAnswerMustBeRightQuiz extends Quiz {

    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public EachAnswerMustBeRightQuiz(QuestionsList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // throws RetryAnswerException if the user should re-try the question;
    // throws OutOfTriesException if user has used up all attempts to answer the question.
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException, OutOfTriesException {
        boolean correct = super.checkAnswer(answer);
        if (!correct) {
            throw new RetryAnswerException("Wrong answer, please retry.");
        }
        return "";
    }

}
