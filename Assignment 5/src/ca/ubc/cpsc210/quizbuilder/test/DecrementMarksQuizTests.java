package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.TimeIsUpException;
import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.question.TrueFalseQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.DecrementMarksQuiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DecrementMarksQuizTests extends QuizBuilderTests {

    private Question q1, q2, q3;
    private QuestionsList qList;
    private Quiz testQuiz;

    @Before
    public void runBefore() {
        // feel free to modify this as needed
        q1 = new TrueFalseQuestion(8, "You are awesome.", true);
        q2 = new TrueFalseQuestion(16, "Donuts are bad for you.", true);
        q3 = new TrueFalseQuestion(1, "You suck at programming", true);
        qList = new QuestionsList();
        qList.addQuestion(q1);
        qList.addQuestion(q2);
        qList.addQuestion(q3);

        testQuiz = new DecrementMarksQuiz(qList);
    }

    @Test
    public void testValidAnswer() {
        testQuiz.startQuiz();
        testQuiz.getNextQuestion();

        assertTrue(testQuiz.checkAnswer("true"));
        assertEquals(25, testQuiz.getMaxMark());

        testQuiz.getNextQuestion();
        assertTrue(testQuiz.checkAnswer("true"));
        assertEquals(25, testQuiz.getMaxMark());
        assertEquals(24, testQuiz.getMarkSoFar());
    }

    @Test (expected = RetryAnswerException.class)
    public void testRetryAnswer() throws OutOfTriesException, RetryAnswerException, TimeIsUpException {
        testQuiz.startQuiz();
        testQuiz.getNextQuestion();
        testQuiz.submitAnswer("false");
        assertEquals(24, testQuiz.getMaxMark());
    }

    @Test (expected = OutOfTriesException.class)
    public void testOutOfTriesQuestion() throws OutOfTriesException, RetryAnswerException, TimeIsUpException {

        testQuiz.startQuiz();
        testQuiz.getNextQuestion();
        testQuiz.submitAnswer("true");

        testQuiz.getNextQuestion();
        testQuiz.submitAnswer("true");

        testQuiz.getNextQuestion();
        testQuiz.submitAnswer("false");
    }
}
