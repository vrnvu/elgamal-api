package elgamal;

import java.math.BigInteger;

public interface Cryptosystem {

    void generatePrivateKey();

    void generateCyclicGroup(int bitSize);

    void printDetails();

    Vote encrypt(BigInteger plain);
    BigInteger decrypt(Vote vote);

}
