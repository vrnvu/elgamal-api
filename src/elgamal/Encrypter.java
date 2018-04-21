package elgamal;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Encrypter {

    private static final BigInteger ONE = BigInteger.ONE;
    private BigInteger grade;
    private BigInteger g;
    private BigInteger h;

    public Encrypter(BigInteger grade, BigInteger g, BigInteger h) {
        this.grade = grade;
        this.g = g;
        this.h = h;
    }

    public Vote encrypt(BigInteger plain) {
        BigInteger m = plain.mod(grade);
        BigInteger random = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(2, grade.subtract(ONE).longValue()));
        BigInteger c1 = g.modPow(random, grade);
        BigInteger s = h.modPow(random, grade);
        BigInteger c2 = m.multiply(s).mod(grade);
        return new Vote(c1, c2);
    }

    public BigInteger decrypt(Vote vote, BigInteger order, BigInteger x) {
        BigInteger inverse_s = vote.getC1().modPow(order.subtract(x), grade);
        return inverse_s.multiply(vote.getC2()).mod(grade);
    }

    public Vote encryptHomomorphic(BigInteger plain) {
        BigInteger m = plain.mod(grade);
        m = g.modPow(m, grade);
        BigInteger random = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(2, grade.subtract(ONE).longValue()));
        BigInteger c1 = g.modPow(random, grade);
        BigInteger s = h.modPow(random, grade);
        BigInteger c2 = m.multiply(s).mod(grade);
        return new Vote(c1, c2);
    }
}
