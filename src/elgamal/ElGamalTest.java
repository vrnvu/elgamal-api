package elgamal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ElGamalTest {

    private ElGamal gamal;
    private long r;
    private BigInteger number;

    @BeforeEach
    void init() {
        gamal = new ElGamal();
        r = ThreadLocalRandom.current().nextLong(0, gamal.getGrade().subtract(gamal.ONE).longValue());
        number = new BigInteger(String.valueOf(r));
    }

    @Test
    void encryptOne() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

    @Test
    void encryptTwo() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

    @Test
    void encryptThree() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

    @Test
    void homomorphic() {
        BigInteger message = new BigInteger(String.valueOf("12"));
        Vote vote1 = gamal.encryptHomomorphic(message);
        System.out.println(message.toString());
        BigInteger decryptedAddition = gamal.decryptHomomorphic(vote1);
        System.out.println(decryptedAddition.toString());
    }

}