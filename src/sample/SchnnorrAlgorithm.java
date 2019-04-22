package sample;

import java.math.BigInteger;
import java.util.*;

public class SchnnorrAlgorithm {
    // Constraints
    private final BigInteger one = new BigInteger("1");


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
        this.q = this.getFactorOfPrime(this.p, 140);
        this.a = this.getValueOfa(this.q, this.p);
        if(a.equals(one)){
            this.generatePublicVariables();
        }
    }

    private BigInteger getPrime(int bitLength){
        Random rand = new Random();
        return BigInteger.probablePrime(bitLength, rand);
    }

    public BigInteger getFactorOfPrime(BigInteger p, int bitLength){
        BigInteger zero = new BigInteger("0");
        BigInteger q =  this.getPrime(bitLength);
        BigInteger prime = p.add(new BigInteger("-1"));
        while (!prime.remainder(q).equals(zero)){
            q = this.getPrime(bitLength);
        }
        return  q;
    }

    public BigInteger getValueOfa(BigInteger q, BigInteger p){
        int power = this.one.divide(q).intValue();
        return this.one.mod(p).pow(power);
    }

    public void generateKeys(){


    }
}
