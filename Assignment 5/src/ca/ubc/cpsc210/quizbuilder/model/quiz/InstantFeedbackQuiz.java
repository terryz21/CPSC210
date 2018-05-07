package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that provides as much feedback to the user as possible.
 */
public class InstantFeedbackQuiz extends Quiz {

    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public InstantFeedbackQuiz(QuestionsList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string that
    // specifies if answer was correct and mark earned so far
    @Override
    public String submitAnswer(String answer) {
        boolean correct = super.checkAnswer(answer);
        String ret = "";
        if (correct) {
            ret += "Correct! Your mark so far is " + this.getMarkSoFar()
                    + " out of " + this.getMaxMark();
        } else {
            ret += "Incorrect! Your mark so far is " + this.getMarkSoFar()
                    + " out of " + this.getMaxMark();
        }
        return ret;
    }

    // MODIFIES: this
    // EFFECTS: Ends the quiz. Returns a string to tell user their final mark
    // and that they did great (regardless of the mark).
    @Override
    public String endQuiz() {
        String ret = super.endQuiz();
        ret += "\nYou did great!";
        return ret;
    }
}
