package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ElGamal implements Cryptosystem {

    private BigInteger x;
    private BigInteger h;
    private BigInteger g;
    private BigInteger order;
    private BigInteger grade;

    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
    private Random r;


    // https://crypto.stackexchange.com/questions/1451/elgamal-multiplicative-cyclic-group-and-key-generation?rq=1
    public ElGamal() {
        r = new SecureRandom();
        generateCyclicGroup(50);
        generatePrivateKey();
    }

    @Override
    public void generatePrivateKey() {
        long r = ThreadLocalRandom.current().nextLong(0, grade.subtract(ONE).longValue());
        x = new BigInteger(String.valueOf(r));
        h = g.modPow(x, grade);
    }

    @Override
    public void generateCyclicGroup(int bitSize) {
        boolean gradeIsPrime;
        do {
            order = new BigInteger(bitSize, 10, r);
            grade = TWO.multiply(order).add(BigInteger.ONE);
            gradeIsPrime = grade.isProbablePrime(10);
            } while (!gradeIsPrime);

        do {
            long randomNum = ThreadLocalRandom.current().nextLong(2, grade.subtract(ONE).longValue());
            g = new BigInteger(String.valueOf(randomNum));
        } while (g.modPow(TWO, grade).equals(ONE));

        if(!g.modPow(order, grade).equals(ONE)) {
            g = g.modPow(TWO, grade);
        }

    }

    @Override
    public void printDetails() {
        System.out.println(String.format("order=%d grade=%d g=%d h=%d",
                this.order.longValue(), this.grade.longValue(), this.g.longValue(),
                this.h.longValue(),  this.x.longValue()));
    }

    @Override
    public Vote encrypt(BigInteger plain) {
        BigInteger m = plain.mod(grade);
        BigInteger random = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(2, grade.subtract(ONE).longValue()));
        BigInteger c1 = g.modPow(random, grade);
        BigInteger s = h.modPow(random, grade);
        BigInteger c2 = m.multiply(s).mod(grade);
        return new Vote(c1, c2);
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        BigInteger inverse_s = vote.getC1().modPow(order.subtract(x), grade);
        return inverse_s.multiply(vote.getC2()).mod(grade);
    }

    public BigInteger getPublicKey() {
        return h;
    }

    public BigInteger getGenerator() {
        return g;
    }

    public BigInteger getGrade() {
        return grade;
    }

}
