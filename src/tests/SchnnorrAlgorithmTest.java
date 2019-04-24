package tests;

import org.junit.Assert;
import org.junit.Test;
import sample.SchnorrAlgorithm;

import java.math.BigInteger;
import java.util.stream.IntStream;

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


        BigInteger p5 = BigInteger.valueOf(11);
        BigInteger q5 = BigInteger.valueOf(5);
        int[] possibleValues = {3,4,5,9};
        int result = algorithm.getCoefficient(q5,p5).intValue();
        Boolean check5 = IntStream.of(possibleValues).anyMatch(x -> x == result);
        Assert.assertTrue(check5);
    }


    @Test
    public void getRandomLessThanTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();

        BigInteger lessThan1 = BigInteger.valueOf(60);
        BigInteger privateKey1 = algorithm.getRandomLessThan(lessThan1);
        Assert.assertTrue(privateKey1.compareTo(lessThan1) == -1);

        BigInteger lessThan2 = BigInteger.valueOf(111);
        BigInteger privateKey2 = algorithm.getRandomLessThan(lessThan2);
        Assert.assertTrue(privateKey2.compareTo(lessThan2) == -1);

        BigInteger lessThan3 = BigInteger.valueOf(133);
        BigInteger privateKey3 = algorithm.getRandomLessThan(lessThan3);
        Assert.assertTrue(privateKey3.compareTo(lessThan3) == -1);
    }

    @Test
    public void getPublicKeyTest() {
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();

        BigInteger a1 = BigInteger.valueOf(11444);
        BigInteger s1 = BigInteger.valueOf(357);
        BigInteger p1 = BigInteger.valueOf(48731);
        int expectedValue1 = 7355;
        int result1 = algorithm.getPublicKey(a1, s1, p1).intValue();
        Assert.assertEquals(expectedValue1, result1);

        BigInteger a2 = BigInteger.valueOf(3);
        BigInteger s2 = BigInteger.valueOf(0);
        BigInteger p2 = BigInteger.valueOf(11);
        int expectedValue2 = 1;
        int result2 = algorithm.getPublicKey(a2, s2, p2).intValue();
        Assert.assertEquals(expectedValue2, result2);

        BigInteger a3 = BigInteger.valueOf(4);
        BigInteger s3 = BigInteger.valueOf(1);
        BigInteger p3 = BigInteger.valueOf(11);
        int expectedValue3 = 3;
        int result3 = algorithm.getPublicKey(a3, s3, p3).intValue();
        Assert.assertEquals(expectedValue3, result3);

        BigInteger a4 = BigInteger.valueOf(5);
        BigInteger s4 = BigInteger.valueOf(3);
        BigInteger p4 = BigInteger.valueOf(11);
        int expectedValue4 = 3;
        int result4 = algorithm.getPublicKey(a4, s4, p4).intValue();
        Assert.assertEquals(expectedValue4, result4);

        BigInteger a5 = BigInteger.valueOf(9);
        BigInteger s5 = BigInteger.valueOf(4);
        BigInteger p5 = BigInteger.valueOf(11);
        int expectedValue5 = 9;
        int result5 = algorithm.getPublicKey(a5, s5, p5).intValue();
        Assert.assertEquals(expectedValue5, result5);
    }

    /* Signing tests */

    @Test
    public void getSignXValueTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();
        BigInteger a1 = BigInteger.valueOf(11444);
        BigInteger r1 = BigInteger.valueOf(274);
        BigInteger p1 = BigInteger.valueOf(48731);
        BigInteger expectedResult1 = BigInteger.valueOf(37123);
        BigInteger result1 = algorithm.getSignXValue(a1, r1, p1);
        Assert.assertEquals(expectedResult1, result1);

        BigInteger a2 = BigInteger.valueOf(3);
        BigInteger r2 = BigInteger.valueOf(0);
        BigInteger p2 = BigInteger.valueOf(11);
        BigInteger expectedResult2 = BigInteger.valueOf(1);
        BigInteger result2 = algorithm.getSignXValue(a2, r2, p2);
        Assert.assertEquals(expectedResult2, result2);

        BigInteger a3 = BigInteger.valueOf(4);
        BigInteger r3 = BigInteger.valueOf(2);
        BigInteger p3 = BigInteger.valueOf(11);
        BigInteger expectedResult3 = BigInteger.valueOf(5);
        BigInteger result3 = algorithm.getSignXValue(a3, r3, p3);
        Assert.assertEquals(expectedResult3, result3);

        BigInteger a4 = BigInteger.valueOf(5);
        BigInteger r4 = BigInteger.valueOf(2);
        BigInteger p4 = BigInteger.valueOf(11);
        BigInteger expectedResult4 = BigInteger.valueOf(3);
        BigInteger result4 = algorithm.getSignXValue(a4, r4, p4);
        Assert.assertEquals(expectedResult4, result4);

        BigInteger a5 = BigInteger.valueOf(9);
        BigInteger r5 = BigInteger.valueOf(4);
        BigInteger p5 = BigInteger.valueOf(11);
        BigInteger expectedResult5 = BigInteger.valueOf(5);
        BigInteger result5 = algorithm.getSignXValue(a5, r5, p5);
        Assert.assertEquals(expectedResult5, result5);
    }

    @Test
    public void getSignYValueTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();
        BigInteger r1 = BigInteger.valueOf(274);
        BigInteger s1 = BigInteger.valueOf(357);
        BigInteger e1 = BigInteger.valueOf(129);
        BigInteger q1 = BigInteger.valueOf(443);
        BigInteger expectedResult1 = BigInteger.valueOf(255);
        BigInteger result1 = algorithm.getSignYValue(r1, s1, e1, q1);
        Assert.assertEquals(expectedResult1, result1);

        BigInteger r2 = BigInteger.valueOf(0);
        BigInteger s2 = BigInteger.valueOf(0);
        BigInteger e2 = BigInteger.valueOf(1);
        BigInteger q2 = BigInteger.valueOf(5);
        BigInteger expectedResult2 = BigInteger.valueOf(0);
        BigInteger result2 = algorithm.getSignYValue(r2, s2, e2, q2);
        Assert.assertEquals(expectedResult2, result2);

        BigInteger r3 = BigInteger.valueOf(2);
        BigInteger s3 = BigInteger.valueOf(1);
        BigInteger e3 = BigInteger.valueOf(2);
        BigInteger q3 = BigInteger.valueOf(5);
        BigInteger expectedResult3 = BigInteger.valueOf(4);
        BigInteger result3 = algorithm.getSignYValue(r3, s3, e3, q3);
        Assert.assertEquals(expectedResult3, result3);

        BigInteger r4 = BigInteger.valueOf(2);
        BigInteger s4 = BigInteger.valueOf(3);
        BigInteger e4 = BigInteger.valueOf(2);
        BigInteger q4 = BigInteger.valueOf(5);
        BigInteger expectedResult4 = BigInteger.valueOf(3);
        BigInteger result4 = algorithm.getSignYValue(r4, s4, e4, q4);
        Assert.assertEquals(expectedResult4, result4);

        BigInteger r5 = BigInteger.valueOf(4);
        BigInteger s5 = BigInteger.valueOf(4);
        BigInteger e5 = BigInteger.valueOf(1);
        BigInteger q5 = BigInteger.valueOf(5);
        BigInteger expectedResult5 = BigInteger.valueOf(3);
        BigInteger result5 = algorithm.getSignYValue(r5, s5, e5, q5);
        Assert.assertEquals(expectedResult5, result5);
    }

    /* Sign verification tests */
    @Test
    public void verifyXValueTest(){
        //   verifyXValue(a, v, y, e, p);
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();

        BigInteger a1 = BigInteger.valueOf(11444);
        BigInteger v1 = BigInteger.valueOf(7355);
        BigInteger y1 = BigInteger.valueOf(255);
        BigInteger e1 = BigInteger.valueOf(129);
        BigInteger p1 = BigInteger.valueOf(48731);
        BigInteger expectedResult1 = BigInteger.valueOf(37123);
        BigInteger result1 = algorithm.verifyXValue(a1, v1, y1, e1, p1);
        Assert.assertEquals(expectedResult1, result1);

        BigInteger a2 = BigInteger.valueOf(3);
        BigInteger v2 = BigInteger.valueOf(1);
        BigInteger y2 = BigInteger.valueOf(0);
        BigInteger e2 = BigInteger.valueOf(1);
        BigInteger p2 = BigInteger.valueOf(11);
        BigInteger expectedResult2 = BigInteger.valueOf(1);
        BigInteger result2 = algorithm.verifyXValue(a2, v2, y2, e2, p2);
        Assert.assertEquals(expectedResult2, result2);

        BigInteger a3 = BigInteger.valueOf(4);
        BigInteger v3 = BigInteger.valueOf(3);
        BigInteger y3 = BigInteger.valueOf(4);
        BigInteger e3 = BigInteger.valueOf(2);
        BigInteger p3 = BigInteger.valueOf(11);
        BigInteger expectedResult3 = BigInteger.valueOf(5);
        BigInteger result3 = algorithm.verifyXValue(a3, v3, y3, e3, p3);
        Assert.assertEquals(expectedResult3, result3);

        BigInteger a4 = BigInteger.valueOf(5);
        BigInteger v4 = BigInteger.valueOf(3);
        BigInteger y4 = BigInteger.valueOf(3);
        BigInteger e4 = BigInteger.valueOf(2);
        BigInteger p4 = BigInteger.valueOf(11);
        BigInteger expectedResult4 = BigInteger.valueOf(3);
        BigInteger result4 = algorithm.verifyXValue(a4, v4, y4, e4, p4);
        Assert.assertEquals(expectedResult4, result4);

        BigInteger a5 = BigInteger.valueOf(9);
        BigInteger v5 = BigInteger.valueOf(9);
        BigInteger y5 = BigInteger.valueOf(3);
        BigInteger e5 = BigInteger.valueOf(1);
        BigInteger p5 = BigInteger.valueOf(11);
        BigInteger expectedResult5 = BigInteger.valueOf(5);
        BigInteger result5 = algorithm.verifyXValue(a5, v5, y5, e5, p5);
        Assert.assertEquals(expectedResult5, result5);
    }

    @Test
    public void signTest(){
        SchnorrAlgorithm algorithm = new SchnorrAlgorithm();
        String message1 = "Ala ma kota";
        algorithm.init();
        BigInteger[] constraints = algorithm.getConstrains();
        BigInteger[] keys = algorithm.getKeys();
        BigInteger p = constraints[0];
        BigInteger q = constraints[1];
        BigInteger a = constraints[2];
        BigInteger s = keys[0];
        BigInteger v = keys[1];

        BigInteger[] sign = algorithm.getSign(message1, q, a, p, s);
        boolean verify = algorithm.verifySign(message1, sign, a, p, v);

        Assert.assertTrue(verify);
    }
}