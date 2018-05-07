package ca.ubc.cs.cpsc210.productrating.tests;

import ca.ubc.cs.cpsc210.productrating.model.Rating;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// unit tests for the Rating class
public class RatingTest {
    private static final double DELTA = 1.0e-12;
    private Rating rating;

    @Before
    public void runBefore() {
        rating = new Rating();
    }

    @Test
    public void testRating(){

       assertEquals(0,rating.getNumVotes());
       assertEquals(0,rating.getNumVotes());
    }

    @Test
    public void testaddSingleVote() {
        rating.addVote(0);
        assertEquals(1,rating.getNumVotes());
    }

    @Test
    public void testaddMultipleVotes() {
        rating.addVote(3);
        rating.addVote(2);
        assertEquals(2,rating.getNumVotes());
    }


    @Test
    public void testresetVote() {
        rating.addVote(0);
        rating.addVote(2);
        assertEquals(2, rating.getNumVotes());
        rating.resetVotes();
        assertEquals(0, rating.getNumVotes());
    }

    @Test
    public void testcomputeSingleScore() {
        rating.addVote(2);
        assertEquals(2.0,rating.computeScore(), DELTA );
    }

    @Test
    public void testcomputeMultipleScores() {
        rating.addVote(2);
        rating.addVote(0);
        rating.addVote(3);
        assertEquals(5.0/3.0,rating.computeScore(), DELTA );
    }

    @Test
    public void testcomputeScores() {
        rating.addVote(1);
        rating.addVote(2);
        assertEquals(1.5,rating.computeScore(),DELTA );
    }

    @Test
    public void testThreeStarString() {
        rating.addVote(3);
        rating.addVote(3);
        assertEquals("***", rating.toString());

    }

    @Test
    public void testTwoStarString() {
        rating.addVote(2);
        rating.addVote(3);
        assertEquals("**", rating.toString());
    }

    @Test
    public void testOneStarString() {
        rating.addVote(1);
        rating.addVote(2);
        assertEquals("*",rating.toString());
    }

    @Test
    public void testFalseOneStarString() {
        rating.addVote(-1);
        rating.addVote(-2);
        assertEquals("Undefined", rating.toString());

    }

    @Test
    public void testZeroStarString() {
        rating.addVote(0);
        rating.addVote(0);
        rating.addVote(1);
        assertEquals("",rating.toString());
    }



    //TODO: design unit tests for Rating class
}