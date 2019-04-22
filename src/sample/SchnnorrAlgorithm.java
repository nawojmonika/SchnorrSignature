package sample;

import java.math.BigInteger;
import java.util.*;

public class SchnnorrAlgorithm {
    // Constraints


    // Public variables
    public BigInteger p; // 512-bit prime
    public BigInteger q; // 140=bit prime & factor of p -1
    public BigInteger a; // a != 1 && a^q === 1(modp)

    // Keys
    public BigInteger s; // private key
    public BigInteger v; // public key

    public void init(){
        this.generatePublicVariables();
        this.generateKeys();
    }

    private void generatePublicVariables(){
        this.p = this.getPrime(512);
        this.q = this.getFactorOfPrime(this.p, 140);
        this.a = this.getValueOfa(this.q, this.p);
        if(this.a.equals(BigInteger.ONE)){
            this.generatePublicVariables();
        }
    }

    private BigInteger getPrime(int bitLength){
        Random rand = new Random();
        return BigInteger.probablePrime(bitLength, rand);
    }

    public BigInteger getFactorOfPrime(BigInteger p, int bitLength){
        BigInteger q =  this.getPrime(bitLength);
        BigInteger prime = p.add(new BigInteger("-1"));
        while (!prime.remainder(q).equals(BigInteger.ZERO)){
            q = this.getPrime(bitLength);
        }
        return  q;
    }

    public BigInteger getValueOfa(BigInteger q, BigInteger p){
        BigInteger e0 = BigInteger.valueOf(2);
        BigInteger a = e0.modPow((p.subtract(BigInteger.ONE).divide(q)), p);
        return a;
    }

    public void generateKeys(){
        this.s = this.getRandomLessThan(100, this.q);
        this.v = getPublicKey(this.a, this.s, this.p);
    }

    public BigInteger getPublicKey(BigInteger a, BigInteger s, BigInteger p){
        return a.modPow(s.negate(), p);
    }

    public BigInteger getRandomLessThan(int bitLength, BigInteger less){
        Random rand = new Random();
        BigInteger random = new BigInteger(bitLength, 1, rand);
        if(random.compareTo(less) != -1){
            this.getRandomLessThan(bitLength,  less);
        }
        return  random;
    }

    public BigInteger[] sign(String M){
        // getting 'e'
        BigInteger r = this.getRandomLessThan(100, this.q);
        String x = this.a.modPow(r, this.p).toString();
        String message = M.concat(x);
        BigInteger e = BigInteger.valueOf(message.hashCode());    // e = H(M,x)
        // getting 'y'
        BigInteger y = (r.add(this.s.multiply(e))).mod(this.q); // y = (r + se) mod q
        BigInteger[] sign = {e, y};
        return sign;
    }
}
