package sample;

import java.math.BigInteger;
import java.util.Random;

public class Controller {
    public BigInteger generatePrivateKey(BigInteger q){
        return getNextRandValue(q);
    }

    private BigInteger getNextRandValue(BigInteger val){
        BigInteger result = val;
        while (result.compareTo(val) >= 0){
            Random rand = new Random();
            result = new BigInteger(val.bitLength(), rand);
        }
        return result;
    }
}
