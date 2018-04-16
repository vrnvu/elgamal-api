package elgamal;

import java.math.BigInteger;

public interface Cryptosystem {

    Vote encrypt(BigInteger plain);
    BigInteger decrypt(Vote vote);

}
