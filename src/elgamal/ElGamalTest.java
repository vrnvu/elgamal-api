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
        r = ThreadLocalRandom.current().nextLong(0, gamal.getGrade().subtract(gamal.getONE()).longValue());
        number = new BigInteger(String.valueOf(r));
    }

    @Test
    void EncryptOne() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

    @Test
    void EncryptTwo() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

    @Test
    void EncryptThree() {
        BigInteger message = new BigInteger(String.valueOf(number));
        Vote vote = gamal.encrypt(message);
        BigInteger result = gamal.decrypt(vote);
        assertEquals(message, result);
    }

}