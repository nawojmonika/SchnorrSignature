package sample;

import java.math.BigInteger;
import java.util.*;

public class SchnorrAlgorithm {
    // Constraints


    // Public variables
    public BigInteger p; // 512-bit prime
    public BigInteger q; // 140-bit prime & factor of p -1
    public BigInteger a; // a != 1 && a^q === 1(modp)

    // Keys
    public BigInteger s; // private key
    public BigInteger v; // public key

    public void init(){
        this.generatePublicVariables();
        this.generateKeys();
    }

    public BigInteger[] getConstrains(){
        BigInteger[] constraints = {this.p, this.q, this.a};
        return  constraints;
    }

    public BigInteger[] getKeys(){
        BigInteger[] keys = {this.s, this.v};
        return  keys;
    }

    private void generatePublicVariables(){
        this.q = this.getRandomPrime(140);
        this.p = this.getPrimeOfFactor(this.q, 373); //512 - 140 + 1
        this.a = this.getCoefficient(this.q, this.p);
    }

    private BigInteger getRandomPrime(int bitLength){
        Random rand = new Random();
        return BigInteger.probablePrime(bitLength, rand);
    }

    public BigInteger getPrimeOfFactor(BigInteger q, int bitLength){
        BigInteger p;
        do{
            Random r = new Random();
            BigInteger k = new BigInteger(bitLength,  r).add(BigInteger.ONE);
            p = (q.multiply(k)).add(BigInteger.ONE);
        }while (!p.isProbablePrime(9999) && p.bitLength() != 512);
        return p;
    }

    public BigInteger getCoefficient(BigInteger q, BigInteger p){
        BigInteger g;
        do {
            BigInteger two = BigInteger.valueOf(2);
            BigInteger a = (two.add(this.getRandomPrime(q.bitLength()))).mod(p);
            BigInteger ga = p.subtract(BigInteger.ONE).divide(q);
            g = a.modPow(ga, p);
        }while (g.equals(BigInteger.ONE));
      return g;
    }

    public void generateKeys(){
        this.s = getPrivateKey(this.q);
        this.v = getPublicKey(this.a, this.s, this.p);
    }

    public BigInteger getPrivateKey(BigInteger q){
        return this.getRandomLessThan(q.bitLength(), q);
    }

    public BigInteger getPublicKey(BigInteger a, BigInteger s, BigInteger p){
        return a.modPow(s.negate(), p);
    }

    public BigInteger getRandomLessThan(int bitLength, BigInteger less){
        BigInteger random;
        do{
            Random rand = new Random();
            random = new BigInteger(bitLength,  rand);
        }while (random.compareTo(less) != -1);

        return  random;
    }

    public BigInteger[] getSign(String M, BigInteger q, BigInteger a, BigInteger p, BigInteger s){
        // getting 'e'
        BigInteger r = this.getRandomLessThan(100, q);
        BigInteger e = getFirstPartOfSign(r, p, M);

        // getting 'y'
        BigInteger y = this.getSecondPartOfSign(r, s, e, q);
        BigInteger[] sign = {e, y};
        return sign;
    }

    public BigInteger getFirstPartOfSign(BigInteger r, BigInteger p, String M){
        BigInteger x = a.modPow(r, p);
        return  this.concatAndHash(M, x);
    }

    public BigInteger getSecondPartOfSign(BigInteger r, BigInteger s, BigInteger e, BigInteger q){
        return (r.add(s.multiply(e))).mod(q); // y = (r + se) mod q
    }


    public  boolean verifySign(String M, BigInteger[] sign, BigInteger a, BigInteger p, BigInteger v){
        BigInteger e = sign[0];
        BigInteger y = sign[1];

        BigInteger x1 = a.modPow(y, p);  //a^y
        BigInteger x2 = v.modPow(e, p);  //v^e

        BigInteger  x = x1.multiply(x2).mod(p); // a^y*v^e mod p

        BigInteger ev = concatAndHash(M, x);

        return e.equals(ev);
    }

    public BigInteger concatAndHash(String M, BigInteger x){
        String message = M.concat(x.toString());
        BigInteger e = BigInteger.valueOf(message.hashCode()).abs();    // e = H(M,x)
        return e;
    }
}
