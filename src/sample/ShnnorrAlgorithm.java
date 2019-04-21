package sample;

import java.math.BigInteger;
import java.util.*;

public class ShnnorrAlgorithm {
    // Public variables
    public BigInteger p; // 512-bit prime
    public BigInteger q; // 140=bit prime & factor of p -1
    public BigInteger a; // a != 1 && a^q === 1(modp)

    // Keys
    public BigInteger s; // private key
    public BigInteger v; // public key

    public void init(){
        this.generatePublicVariables();
    }

    private void generatePublicVariables(){
        this.p = this.getPrime(512);
        this.q = this.getFactorOfPrime();
        this.a = this.getValueOfa();
    }

    private BigInteger getPrime(int bitLength){
        Random rand = new Random();
        return BigInteger.probablePrime(bitLength, rand);
    }

    private BigInteger getFactorOfPrime(){
        BigInteger zero = new BigInteger("0");
        BigInteger q =  this.getPrime(140);
        BigInteger prime = this.p.add(new BigInteger("-1"));
        while (prime.remainder(q) != zero){
            q = this.getPrime(140);
        }
        return  q;
    }

    private BigInteger getValueOfa(){
        BigInteger one = new BigInteger("1");
        BigInteger power = one.divide(this.q);
        return one.modPow(power,this.p);
    }

    public void generateKeys(){


    }
}
