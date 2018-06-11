package elgamal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectionTallyTest {

    private ElectionTally tally;
    private final static int capacity = 100;
    private final static int yes = 101;
    private final static int no = 1;


    @BeforeEach
    void setUp() {
        tally = new ElectionTally(capacity, yes, no);
    }

    @Test
    void allVotesAreYes() {
        int result = capacity * yes;
        tally.setResult(result);
        tally.tallyResult();
        int yesCount = tally.getYesCount();
        int noCount = tally.getNoCount();
        assertEquals(capacity, yesCount);
        assertEquals(0, noCount);
    }


    @Test
    void allVotesAreNo() {
        int result = capacity * no;
        tally.setResult(result);
        tally.tallyResult();
        int yesCount = tally.getYesCount();
        int noCount = tally.getNoCount();
        assertEquals(0, yesCount);
        assertEquals(capacity, noCount);
    }


    @Test
    void voteCase1() {
        int yesVotes = 45 * yes;
        int noVotes = 55 * no;
        int result = yesVotes + noVotes;
        tally.setResult(result);
        tally.tallyResult();
        int yesCount = tally.getYesCount();
        int noCount = tally.getNoCount();
        assertEquals(45, yesCount);
        assertEquals(55, noCount);
    }

}