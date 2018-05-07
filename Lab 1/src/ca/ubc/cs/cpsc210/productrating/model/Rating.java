package ca.ubc.cs.cpsc210.productrating.model;



// Represents a rating (for a product or movie or ...)
// that is based on an arbitrary number of votes on a scale
// of 0 to MAX_VOTE stars
public class Rating {
    public static final int MAX_VOTE = 3;

    private int Score_So_Far;
    private int Entries_So_Far;




    //EFFECTS:initializes a new Rating with no initial votes
    public Rating() {
        Score_So_Far = 0;
        Entries_So_Far = 0;

    }

    //MODIFIES: this
    //EFFECTS: resets number of votes to zero
    public void resetVotes() {
        this.Entries_So_Far = 0;
    }

    //EFFECTS: returns current number of votes
    public int getNumVotes() {
        return this.Entries_So_Far;
    }

    //REQUIRES: newVote must be >= 0 and <= 3
    //MODIFIES: this
    //EFFECTS: newVote is added to scoresofar and entriessofar increases by 1
    public void addVote(int newVote) {
        Score_So_Far += newVote;
        Entries_So_Far++;
    }

    //REQUIRES: number of votes in votingList >= 1
    //EFFECTS: returns average of all votes casted so far
    public double computeScore() {
        return (double) this.Score_So_Far  / this.Entries_So_Far;
    }


    //REQUIRES: number of votes in votingList >= 1
    //EFFECTS: returns String consisting of computeScore's stars rounded down to nearest star
    public String toString() {
        if (this.computeScore() >= 0 && this.computeScore() < 1) {
            return "";
        } else if (this.computeScore() >= 1 && this.computeScore() < 2) {
            return "*";
        } else if (this.computeScore() >= 2 && this.computeScore() < 3) {
            return "**";
        } else if (this.computeScore() == 3) {
            return "***";
        }
        return "Undefined";
    }



    // TODO: Add specification (do not implement methods - write stubs only)
    // TODO: After tests have been designed: add fields
    // TODO: After adding fields: replace method stubs with full implementation
}
