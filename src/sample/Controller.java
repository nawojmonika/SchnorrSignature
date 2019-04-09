package sample;

import java.math.BigInteger;
import java.util.*;

public class Controller {
    public BigInteger getSecretKey(BigInteger factor){
        return nextRandomBigInteger(factor); // 0 < s < q
    }

    public BigInteger getPublicKey(BigInteger alpha, BigInteger secretKey, BigInteger prime){
        return alpha.modPow(secretKey.negate(), prime); // a ^-s mod p
    }

    private BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while (result.compareTo(n) >= 0) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }
}
