package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ElGamal implements Cryptosystem {

    private BigInteger x;
    private BigInteger h;
    private BigInteger generator;
    private BigInteger order;
    private BigInteger grade;

    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
    private Random r;


    // https://crypto.stackexchange.com/questions/1451/elgamal-multiplicative-cyclic-group-and-key-generation?rq=1
    public ElGamal() {
        r = new SecureRandom();
        generateCyclicGroup();
        generatePrivateKey();
    }

    private void generatePrivateKey() {
        int r = ThreadLocalRandom.current().nextInt(1, grade.subtract(ONE).intValue());
        x = new BigInteger(String.valueOf(r)).mod(order);
        h = generator.pow(x.intValue());
    }

    private void generateCyclicGroup() {
        // BigInteger(int bitLength, int certainty, Random rnd)
        // isProbablePrime(int certainty)

        boolean gradeIsPrime;
        do {
            order = new BigInteger(6, 10, r);
            grade = TWO.multiply(order).add(BigInteger.ONE);
            gradeIsPrime = grade.isProbablePrime(10);
            } while (!gradeIsPrime);

        boolean isGenerator;
        do {
            // How to efficient find a generator?
            int randomNum = ThreadLocalRandom.current().nextInt(0, grade.subtract(ONE).intValue());
            generator = new BigInteger(String.valueOf(randomNum));
            // Choose an element g of Fp with order n
            isGenerator = generator.modPow(order, grade).equals(ONE);
        } while (!generator.modPow(TWO, grade).equals(ONE));

    }

    @Override
    public Vote encrypt(BigInteger plain) {
        int y = ThreadLocalRandom.current().nextInt(1, grade.subtract(ONE).intValue());
        BigInteger c1 = generator.pow(y);
        BigInteger s = h.pow(y);
        // Map message "plain" to an element m' of G
        BigInteger c2 = plain.multiply(s);
        return new Vote(c1, c2);
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        // When is needed?

        BigInteger s = vote.getC1().pow(x.intValue());
        //BigInteger m1 = vote.getC2().pow(s.pow(-1).intValue());
        // Optimization consequence of Lagrange Theorem
        // inverse_ s = c1 ^ (q-x) = g ^ (q-x)y
        BigInteger inverse_s = vote.getC1().pow((grade.subtract(new BigInteger(String.valueOf(x))).mod(order)).intValue());
        return inverse_s.multiply(vote.getC2());
    }

    public static void main(String[] args) {
        ElGamal gamal = new ElGamal();
        BigInteger message1 = new BigInteger("1");
        System.out.println("Before encryption: " + message1.toString());
        System.out.println(String.format("%d %d %d %d", gamal.order, gamal.grade, gamal.generator, gamal.h,  gamal.x));

        BigInteger message2 = new BigInteger("2");
        System.out.println("Before encryption: " + message2.toString());
        System.out.println(String.format("%d %d %d %d", gamal.order, gamal.grade, gamal.generator, gamal.h,  gamal.x));

        BigInteger message3 = new BigInteger("3");
        System.out.println("Before encryption: " + message3.toString());
        System.out.println(String.format("%d %d %d %d", gamal.order, gamal.grade, gamal.generator, gamal.h,  gamal.x));

        Vote v1 = gamal.encrypt(message1);
        Vote v2 = gamal.encrypt(message2);
        Vote v3 = gamal.encrypt(message3);
        BigInteger r1 = gamal.decrypt(v1);
        BigInteger r2 = gamal.decrypt(v2);
        BigInteger r3 = gamal.decrypt(v3);
        System.out.println("After decrypt: " +r1.toString());
        System.out.println("After decrypt: " +r2.toString());
        System.out.println("After decrypt: " +r3.toString());
    }
}
