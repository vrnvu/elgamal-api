package elgammal;

import java.math.BigInteger;

public class ElGammal implements Cryptosystem {

    private final BigInteger privateKey;
    private final BigInteger g;
    private final BigInteger q;

    public ElGammal() {
        privateKey = null;
        g = null;
        q = null;
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
