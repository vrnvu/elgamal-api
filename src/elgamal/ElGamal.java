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
        int r = ThreadLocalRandom.current().nextInt(0, grade.subtract(ONE).intValue());
        x = new BigInteger(String.valueOf(r));
        h = g.modPow(x, grade);
    }

    private void generateCyclicGroup() {
        boolean gradeIsPrime;
        do {
            order = new BigInteger(3, 10, r);
            grade = TWO.multiply(order).add(BigInteger.ONE);
            gradeIsPrime = grade.isProbablePrime(10);
            } while (!gradeIsPrime);

        do {
            int randomNum = ThreadLocalRandom.current().nextInt(2, grade.subtract(ONE).intValue());
            g = new BigInteger(String.valueOf(randomNum));
        } while (g.modPow(TWO, grade).equals(ONE));

        if(!g.modPow(order, grade).equals(ONE)) {
            g = g.modPow(TWO, grade);
        }

    }

    public void printGroupDetails() {
        System.out.println(String.format("order=%d grade=%d g=%d h=%d",
                this.order.intValue(), this.grade.intValue(), this.g.intValue(),
                this.h.intValue(),  this.x.intValue()));
    }

    @Override
    public Vote encrypt(BigInteger plain) {
        BigInteger m = plain.mod(grade);
        BigInteger random = BigInteger.valueOf(ThreadLocalRandom.current().nextInt(2, grade.subtract(ONE).intValue()));
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

    public static void main(String[] args) {
        ElGamal gamal = new ElGamal();
        gamal.printGroupDetails();

        BigInteger message1 = new BigInteger("1");
        System.out.println("Before encryption: " + message1.toString());
        BigInteger message2 = new BigInteger("2");
        System.out.println("Before encryption: " + message2.toString());
        BigInteger message3 = new BigInteger("3");
        System.out.println("Before encryption: " + message3.toString());
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
