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
    private Encrypter encrypter;

    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
    private Random r;

    public ElGamal() {
        r = new SecureRandom();
        generateCyclicGroup(10);
        generatePrivateKey();
        setEncrypter();
    }

    private void setEncrypter() {
        encrypter = new Encrypter(grade, g, h);
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
            gradeIsPrime = generateOrderGrade(bitSize);
            } while (!gradeIsPrime);

        do {
            genereateGroupGenerator();
        } while (g.modPow(TWO, grade).equals(ONE));

        if(!g.modPow(order, grade).equals(ONE)) {
            g = g.modPow(TWO, grade);
        }

    }

    private void genereateGroupGenerator() {
        long randomNum = ThreadLocalRandom.current().nextLong(2, grade.subtract(ONE).longValue());
        g = new BigInteger(String.valueOf(randomNum));
    }

    private boolean generateOrderGrade(int bitSize) {
        boolean gradeIsPrime;
        order = new BigInteger(bitSize, 10, r);
        grade = TWO.multiply(order).add(BigInteger.ONE);
        gradeIsPrime = grade.isProbablePrime(10);
        return gradeIsPrime;
    }

    @Override
    public void printDetails() {
        System.out.println(String.format("order=%d grade=%d g=%d h=%d",
                this.order.longValue(), this.grade.longValue(), this.g.longValue(),
                this.h.longValue(),  this.x.longValue()));
    }

    @Override
    public Vote encrypt(BigInteger plain) {
        return encrypter.encrypt(plain);
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        return encrypter.decrypt(vote, order, x);
    }

    public Vote encryptHomomorphic(BigInteger plain) {
        return encrypter.encryptHomomorphic(plain);
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
