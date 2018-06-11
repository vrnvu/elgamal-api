package elgamal;

public class ElectionTally {

    private final int capacity;
    private final int yes;
    private final int no;

    private int result;

    private int yesCount = 0;
    private int noCount = 0;

    public ElectionTally(int capacity, int yes, int no) {
        this.capacity = capacity;
        this.yes = yes;
        this.no = no;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void tallyResult() {
        yesCount = result / yes;
        noCount = result % yes;
    }

    public int getYesCount() {
        return yesCount;
    }

    public int getNoCount() {
        return noCount;
    }
}
