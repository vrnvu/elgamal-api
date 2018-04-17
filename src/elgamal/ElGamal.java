package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ElGamal implements Cryptosystem {

    private BigInteger privateKey;
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
        int randomNum = ThreadLocalRandom.current().nextInt(1, grade.subtract(ONE).intValue());
        privateKey = generator.pow(randomNum);
    }

    private void generateCyclicGroup() {
        // BigInteger(int bitLength, int certainty, Random rnd)
        // isProbablePrime(int certainty)

        boolean gradeIsPrime;
        do {
            order = new BigInteger(3, 10, r);
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
        } while (!isGenerator && !generator.modPow(TWO, grade).equals(ONE));

    }

    @Override
    public Vote encrypt(BigInteger plain) {
        return null;
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        return null;
    }

    public static void main(String[] args) {
        ElGamal gamal = new ElGamal();
    }
}
