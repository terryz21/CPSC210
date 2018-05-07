package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Created by terryzhang on 2017-08-01.
 */
public class DecrementMarksQuiz extends EachAnswerMustBeRightQuiz {


    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public DecrementMarksQuiz(QuestionsList questions) {
        super(questions);
    }


    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // if answer is incorrect, user can retry but maximum mark is decremented by one
    // throws OutOfTriesException if user has used up all attempts (maximum mark decremented to 0)
    // to answer the question.
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException, OutOfTriesException {
        boolean correct = super.checkAnswer(answer);

        double maxMark = this.curQuestion.getMaxMark();

        if (!correct && maxMark > 1.0) {
            maxMark = maxMark -1.0;
            this.curQuestion.setMaxMark(maxMark);
            throw new RetryAnswerException("Wrong answer, please retry.");
        }
        else if (maxMark == 1.0){
            throw new OutOfTriesException("You have used up all your attempts");
        }
        return "";
    }

}
