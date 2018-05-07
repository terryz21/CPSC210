package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.TimeIsUpException;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;

import static org.junit.Assert.fail;

//NOTE: these helper methods are not designed to be used with InstantFeedbackQuiz
public abstract class QuizBuilderTests {

    public void tryWrongAnswer(Quiz q, String answer) {
        try {
            q.submitAnswer(answer);
        } catch (RetryAnswerException e) {
            return;
        } catch (OutOfTriesException e){
            return;
        } catch (TimeIsUpException e) {
            fail("Unexpected TimeIsUpException exception: " + e);
        } catch (NumberFormatException e) {
            fail("Unexpected NumberFormatException: " + e);
        }
        fail("Did not catch the expected RetryAnswerException exception.");
    }

    public void tryRightAnswer(Quiz q, String answer) {
        try {
            q.submitAnswer(answer);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
}
