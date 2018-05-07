package ca.ubc.cpsc210.quizbuilder;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.TimeIsUpException;
import ca.ubc.cpsc210.quizbuilder.model.question.MultiplicationQuestion;
import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.question.TrueFalseQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.RandomizedQuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The class that orchestrates the actual running of the quiz.
 * <p>
 * Run this class as a "Java Application" and follow instructions on the
 * Console.
 * </p>
 */
public class QuizRunner {

    // EFFECTS: returns a list of questions
    public QuestionsList getQuestions() {
        this.print("Welcome to the quiz!\n");

        Question q1 = null, q2 = null, q3 = null;

        q1 = new TrueFalseQuestion(8, "You are awesome.", true);
        q2 = new TrueFalseQuestion(8, "Donuts are bad for you.", true);
        // Un-comment next line to test MultiplicationQuestion:
        q3 = new MultiplicationQuestion(8, 5, 2);

        QuestionsList qList = new RandomizedQuestionsList();
        qList.addQuestion(q1);
        qList.addQuestion(q2);
        // Un-comment next line to test MultiplicationQuestion:
        qList.addQuestion(q3);
        return qList;
    }

    // EFFECTS: prints s to standard out.
    public void print(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }

    // EFFECTS: reads a line of text from standard input and returns it
    private String getUserResponse() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        try {
            line = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read a line of text.");
            System.exit(1);
        }
        return line;
    }

    // ///////////////////////////////////////////////////////

    public static void main(String args[]) {

        QuizRunner qRunner = new QuizRunner();

        QuestionsList qList = qRunner.getQuestions();

        // Build the right quiz based on user's input.
        Quiz quiz = buildQuiz(qRunner, qList);

        // Generic quiz runner:
        runQuiz(qRunner, quiz);

        String endOfQuizString = quiz.endQuiz();
        qRunner.print(endOfQuizString);
        qRunner.print("\nThanks for taking the quiz!\n");
    }

    // EFFECTS: returns quiz built from questions in qList
    private static Quiz buildQuiz(QuizRunner qRunner, QuestionsList qList) {
        Quiz quiz = null;

        do {
            qRunner.print("Enter a number for the type of quiz:");
            qRunner.print("1 : Instant feedback quiz.");
            qRunner.print("2 : Each answer must be right quiz.");
            // Un-comment the following line to interactively test DecrementMarksQuiz:
            qRunner.print("3 : Decrement marks quiz.");
            qRunner.print("4 : A quiz with a timer.");
            qRunner.print("Your response: ");

            String qType = qRunner.getUserResponse();
            if (qType.equals("1")) {
                quiz = new InstantFeedbackQuiz(qList);
            } else if (qType.equals("2")) {
                quiz = new EachAnswerMustBeRightQuiz(qList);
            }
            // Un-comment next 3 lines to interactively test DecrementMarksQuiz:
            else if (qType.equals("3")) {
               quiz = new DecrementMarksQuiz(qList);
            }
            else if (qType.equals("4")) {

                // How long should the quiz be timed for:
                int numSeconds = 0;
                do {
                    qRunner.print("Enter number of seconds for the quiz:");
                    qRunner.print("Your response: ");
                    Scanner in = new Scanner(System.in);
                    numSeconds = in.nextInt();
                } while (numSeconds <= 0);
                quiz = new TimedQuiz(qList, numSeconds);
            }
        } while (quiz == null);
        return quiz;
    }

    // EFFECTS: runs the given quiz
    private static void runQuiz(QuizRunner qRunner, Quiz quiz) {
        quiz.startQuiz();
        while (quiz.anymoreQuestions()) {
            Question q = quiz.getNextQuestion();

            boolean retryQuestion;
            do {
                retryQuestion = false;
                qRunner.print(q.getQuestionString());
                qRunner.print("Your response: ");
                String answer = qRunner.getUserResponse();
                qRunner.print("");

                try {
                    String feedback = quiz.submitAnswer(answer);
                    if (!feedback.equals("")) {
                        qRunner.print(feedback);
                    }
                } catch (RetryAnswerException e) {
                    qRunner.print(e.getMessage());
                    retryQuestion = true;
                } catch (TimeIsUpException e) {
                    qRunner.print(e.getMessage());
                    System.exit(0);
                } catch (OutOfTriesException e) {
                    qRunner.print((e.getMessage()));
                }
            } while (retryQuestion);
        }
    }
}
