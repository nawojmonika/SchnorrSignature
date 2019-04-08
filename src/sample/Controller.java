package sample;

import java.math.BigInteger;
import java.util.*;

public class Controller {
    public BigInteger generatePrivateKey(BigInteger factorOfPrime){
        return getNextRandValue(factorOfPrime); // s < q
    }

    public BigInteger generatePublicKey(BigInteger alpha, BigInteger secreyKey, BigInteger prime){
        return alpha.modPow(secreyKey.negate(), prime); // v = a^(-s) mod p
    }

    private BigInteger getNextRandValue(BigInteger val){
        BigInteger result = val;
        while (result.compareTo(val) >= 0){
            Random rand = new Random();
            result = new BigInteger(val.bitLength(), rand);
        }
        return result;
    }

    private BigInteger getFactorOfPrime(){
        BigInteger factorOfPrime = new BigInteger("2");
        // q = 2^256 - 2^168 + 1
        factorOfPrime = factorOfPrime.pow(256);	// q = 2^256
        factorOfPrime = factorOfPrime.subtract(new BigInteger("2").pow(168));	// q = 2^256 - 2^168
        factorOfPrime = factorOfPrime.add(new BigInteger("1"));	// q = 2^256 - 2^168 + 1
        return factorOfPrime;
    }

    public HashMap<Utils.Globals, BigInteger> getGlobals(){
        HashMap<Utils.Globals, BigInteger> globals = new HashMap();
        globals.put(Utils.Globals.factorOfPrime, this.getFactorOfPrime());
        return globals;
    }

    public BigInteger[] sign(String password, HashMap<Utils.Globals, BigInteger> globals){
        BigInteger r = new BigInteger(password.getBytes()).mod(globals.get(Utils.Globals.factorOfPrime));
    }

}
