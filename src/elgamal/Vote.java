package elgamal;

import java.math.BigInteger;

public class Vote {

    private BigInteger c1;
    private BigInteger c2;

    public Vote(BigInteger c1, BigInteger c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public BigInteger getC1() {
        return c1;
    }

    public void setC1(BigInteger c1) {
        this.c1 = c1;
    }

    public BigInteger getC2() {
        return c2;
    }

    public void setC2(BigInteger c2) {
        this.c2 = c2;
    }
}
