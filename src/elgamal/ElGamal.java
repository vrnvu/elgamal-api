package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class ElGamal implements Cryptosystem {

    private final BigInteger privateKey;
    private final BigInteger generator;
    private final BigInteger order;
    private final BigInteger grade;


    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    public ElGamal() {
        generateCyclicGroup();
        generatePrivateKey();
        privateKey = null;
        generator = null;
        order = null;
        grade = null;

    }

    private void generatePrivateKey() {

    }

    private void generateCyclicGroup() {
        // BigInteger(int bitLength, int certainty, Random rnd)
        Random r = new SecureRandom();
        BigInteger b = new BigInteger(5,20, r);
    }

    @Override
    public Vote encrypt(BigInteger plain) {
        return null;
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        return null;
    }
}
