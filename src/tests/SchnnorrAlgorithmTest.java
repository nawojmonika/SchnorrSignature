package tests;

import org.junit.Assert;
import org.junit.Test;
import sample.SchnorrAlgorithm;

import java.math.BigInteger;
import java.util.Arrays;

public class SchnnorrAlgorithmTest {


    /* Public variables tests */
    @Test
    public void getPrimeOfFactorTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();


        BigInteger q1 = BigInteger.valueOf(2);
        BigInteger p1 = algorithm.getPrimeOfFactor(q1, 513 - q1.bitLength());
        Assert.assertTrue(p1.subtract(BigInteger.ONE).remainder(q1).equals(BigInteger.ZERO));

        BigInteger q2 = BigInteger.valueOf(89);
        BigInteger p2 = algorithm.getPrimeOfFactor(q2, 513 - q2.bitLength());
        Assert.assertTrue(p2.subtract(BigInteger.ONE).remainder(q2).equals(BigInteger.ZERO));

        BigInteger q3 = BigInteger.valueOf(997);
        BigInteger p3 = algorithm.getPrimeOfFactor(q3, 513 - q3.bitLength());
        Assert.assertTrue(p3.subtract(BigInteger.ONE).remainder(q3).equals(BigInteger.ZERO));

        BigInteger q4 = BigInteger.valueOf(9973);
        BigInteger p4 = algorithm.getPrimeOfFactor(q4, 513 - q4.bitLength());
        Assert.assertTrue(p4.subtract(BigInteger.ONE).remainder(q4).equals(BigInteger.ZERO));
    }

    @Test
    public void getCoefficientTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();

        BigInteger p1 = BigInteger.valueOf(11);
        BigInteger q1 = BigInteger.valueOf(5);
        BigInteger a1 = algorithm.getCoefficient(q1, p1);
        BigInteger modCheck1 = a1.modPow(q1, p1);
        Boolean check1 = modCheck1.equals(BigInteger.ONE);
        Assert.assertTrue("Expected value: 1, Actual: " + modCheck1.intValue(),check1);


        BigInteger p2 = BigInteger.valueOf(71);
        BigInteger q2 = BigInteger.valueOf(5);
        BigInteger a2 = algorithm.getCoefficient(q2, p2);
        BigInteger modCheck2 = a2.modPow(q2, p2);
        Boolean check2 = modCheck2.equals(BigInteger.ONE);
        Assert.assertTrue("Expected value: 1, Actual: " + modCheck2.intValue(),check2);

        BigInteger p3 = BigInteger.valueOf(23);
        BigInteger q3 = BigInteger.valueOf(11);
        BigInteger a3 = algorithm.getCoefficient(q3, p3);
        BigInteger modCheck3 = a3.modPow(q3, p3);
        Boolean check3 = modCheck3.equals(BigInteger.ONE);
        Assert.assertTrue("Expected value: 1, Actual: " + modCheck3.intValue(),check3);

        BigInteger p4 = BigInteger.valueOf(67);
        BigInteger q4 = BigInteger.valueOf(11);
        BigInteger a4 = algorithm.getCoefficient(q4, p4);
        BigInteger modCheck4 = a4.modPow(q4, p4);
        Boolean check4 = modCheck4.equals(BigInteger.ONE);
        Assert.assertTrue("Expected value: 1, Actual: " + modCheck4.intValue(),check4);
    }


    @Test
    public void getRandomLessThanTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();

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
//        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();
//
//        String message1 = "Ala ma kota";
//        algorithm.init();
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