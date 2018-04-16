package elgammal;

import java.math.BigInteger;

public class Vote {

    private BigInteger vote;

    public Vote(BigInteger vote) {
        this.vote = vote;
    }

    public BigInteger getVote() {
        return vote;
    }

    public void setVote(BigInteger vote) {
        this.vote = vote;
    }
}
