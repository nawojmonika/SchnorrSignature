package sample;

import java.math.BigInteger;
import java.util.*;

public class Controller {
    public BigInteger factor;
    public BigInteger alpha;
    public BigInteger prime;

    public BigInteger getSecretKey(){
        return nextRandomBigInteger(this.factor); // 0 < s < q
    }

    public BigInteger getPublicKey(BigInteger secretKey){
        return this.alpha.modPow(secretKey.negate(), this.prime); // a ^-s mod p
    }

    private BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while (result.compareTo(n) >= 0) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }

    public void sign(String message){
        BigInteger random = this.nextRandomBigInteger(this.factor); // r < q
        BigInteger X = this.alpha.modPow(random, this.prime);

    }
}
