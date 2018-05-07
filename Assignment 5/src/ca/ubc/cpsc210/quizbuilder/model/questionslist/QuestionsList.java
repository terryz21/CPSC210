package ca.ubc.cpsc210.quizbuilder.model.questionslist;

import java.util.LinkedList;
import java.util.List;

import ca.ubc.cpsc210.quizbuilder.model.question.Question;

/**
 * Represents a list of quiz questions.
 */
public class QuestionsList {
    protected List<Question> questions;

    // EFFECTS: constructs empty list of questions
    public QuestionsList() {
        questions = new LinkedList<Question>();
    }

    // MODIFIES: this
    // EFFECTS: adds q to list of questions
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // MODIFIES: this
    // EFFECTS: removes last question from list, if list is not empty
    public void removeLastQuestion() {
        if (!questions.isEmpty()) {
            int lastQuestionIndex = questions.size() - 1;
            questions.remove(lastQuestionIndex);
        }
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    // EFFECTS: returns number of questions in list
    public int length() {
        return questions.size();
    }

    // EFFECTS: returns the max total mark possible as a sum of the marks across all of
    // the questions.
    public int getMaxMark() {
        int maxMark = 0;
        for (Question q : questions) {
            maxMark += q.getMaxMark();
        }
        return maxMark;
    }
}
