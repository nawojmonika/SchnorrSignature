package sample;

import java.math.BigInteger;
import java.util.*;

public class Controller {
    public BigInteger getPrime(BigInteger factor) {
        return factor.multiply(new BigInteger("2")).add(new BigInteger("1")); //p = 2q + 1
    }

    public BigInteger getFactor() {
        return new BigInteger("0"); //To DO: get from user or generate??    //q
    }
}
