package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.question.MultiplicationQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.InstantFeedbackQuiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class MultiplicationQuestionTest extends QuizBuilderTests {

    private MultiplicationQuestion mq1, mq2, mq3;
    private QuestionsList qList;
    private Quiz testQuiz;


    @Before
    public void runBefore() {
        mq1 = new MultiplicationQuestion(2, 2, 3);
        mq2 = new MultiplicationQuestion(1, 0, 2);
        mq3 = new MultiplicationQuestion(2, 1,1);
        qList = new QuestionsList();
        qList.addQuestion(mq1);
        qList.addQuestion(mq2);
        qList.addQuestion(mq3);

        testQuiz = new InstantFeedbackQuiz(qList);

        // make a new multiplication question, question list
        // and quiz for testing
    }

    @Test
    public void testValidMultiplication() {
        testQuiz.startQuiz();
        testQuiz.getNextQuestion();
        assertTrue(testQuiz.checkAnswer("6"));
        assertEquals(2, testQuiz.getMarkSoFar());

        testQuiz.getNextQuestion();
        assertFalse(testQuiz.checkAnswer("1"));

        testQuiz.getNextQuestion();
        assertTrue(testQuiz.checkAnswer("1"));

        testQuiz.endQuiz();
        // template for your test methods
    }

    @Test
    public void testInvalidMultiplication() {
        testQuiz.startQuiz();
        testQuiz.getNextQuestion();
        assertFalse(testQuiz.checkAnswer("Terry"));

        testQuiz.getNextQuestion();
        assertFalse(testQuiz.checkAnswer("0.0"));
    }
}