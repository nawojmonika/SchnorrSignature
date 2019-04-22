package tests;

import org.junit.Assert;
import org.junit.Test;
import sample.SchnnorrAlgorithm;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class SchnnorrAlgorithmTest {

    SchnnorrAlgorithm algorithm = new SchnnorrAlgorithm();

    /* Public variables tests */
    @Test
    public void getPrimeOfFactorTest(){
        BigInteger sub = BigInteger.valueOf(-1);


        BigInteger q1 = BigInteger.valueOf(2);
        BigInteger p1 = algorithm.getPrimeOfFactor(q1, 513 - q1.bitLength());
        Assert.assertTrue(p1.add(sub).remainder(q1).equals(BigInteger.ZERO));

        BigInteger q2 = BigInteger.valueOf(89);
        BigInteger p2 = algorithm.getPrimeOfFactor(q2, 513 - q2.bitLength());
        Assert.assertTrue(p2.add(sub).remainder(q2).equals(BigInteger.ZERO));

        BigInteger q3 = BigInteger.valueOf(997);
        BigInteger p3 = algorithm.getPrimeOfFactor(q3, 513 - q3.bitLength());
        Assert.assertTrue(p3.add(sub).remainder(q3).equals(BigInteger.ZERO));

        BigInteger q4 = BigInteger.valueOf(9973);
        BigInteger p4 = algorithm.getPrimeOfFactor(q4, 513 - q4.bitLength());
        Assert.assertTrue(p4.add(sub).remainder(q4).equals(BigInteger.ZERO));
    }

    @Test
    public void getRandomLessThanTest(){
        BigInteger lessThan1 = BigInteger.valueOf(60);
        BigInteger privateKey1 = algorithm.getRandomLessThan(lessThan1.bitLength(), lessThan1);
        Assert.assertTrue(privateKey1.compareTo(lessThan1) == -1);

        BigInteger lessThan2 = BigInteger.valueOf(111);
        BigInteger privateKey2 = algorithm.getRandomLessThan(lessThan2.bitLength(), lessThan2);
        Assert.assertTrue(privateKey2.compareTo(lessThan2) == -1);

        BigInteger lessThan3 = BigInteger.valueOf(133);
        BigInteger privateKey3 = algorithm.getRandomLessThan(lessThan3.bitLength(), lessThan3);
        Assert.assertTrue(privateKey3.compareTo(lessThan3) == -1);
    }

//    @Test
//    public void signTest(){
//        String message1 = "Ala ma kota";
//        algorithm.init();
//        System.out.println("After init");
//        BigInteger[] constraints = algorithm.getConstrains();
//        BigInteger[] keys = algorithm.getKeys();
//        BigInteger p = constraints[0];
//        BigInteger q = constraints[1];
//        BigInteger a = constraints[2];
//        BigInteger s = keys[0];
//        BigInteger v = keys[1];
//
//        BigInteger[] sign = algorithm.getSign(message1, q, a, p, s);
//        boolean verify = algorithm.verifySign(message1, sign, a, p, v);
//
//        Assert.assertTrue(verify);
//    }


}