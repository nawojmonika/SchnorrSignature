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
    public void factorOfPrimeTest() {
        // Test of generating 'q' value
        BigInteger prime1 = new BigInteger("7");
        int[] possibleFactors1 = {1, 2, 3, 6};
        int factor1 = algorithm.getFactorOfPrime(prime1, 2).intValue();

        Assert.assertTrue("Expected to be one of: [1, 2, 3, 6] actual: " + factor1, IntStream.of(possibleFactors1).anyMatch(x -> x == factor1));

        BigInteger prime2 = new BigInteger("11");
        int[] possibleFactors2 = {1, 2, 5, 10};
        int factor2 = algorithm.getFactorOfPrime(prime2, 2).intValue();

        Assert.assertTrue("Expected to be one of: [1, 2, 5, 10] actual: " + factor2, IntStream.of(possibleFactors2).anyMatch(x -> x == factor2));

        BigInteger prime3 = new BigInteger("13");
        int[] possibleFactors3 = {1, 2, 3, 6, 12};
        int factor3 = algorithm.getFactorOfPrime(prime3, 2).intValue();

        Assert.assertTrue("Expected to be one of: [1, 2, 3, 6, 12] actual: " + factor3, IntStream.of(possibleFactors3).anyMatch(x -> x == factor3));
    }

    @Test
    public void getValueOfaTest(){
        // Test of generating 'a' value
        BigInteger one = new BigInteger("1");

        BigInteger q1 = new BigInteger("3");
        BigInteger p1 = new BigInteger("7");

        int modp1 = one.mod(p1).intValue();
        BigInteger a1 = algorithm.getValueOfa(q1, p1);
        int check1 = a1.pow(q1.intValue()).intValue();

        Assert.assertEquals(modp1, check1);


        BigInteger q2 = new BigInteger("5");
        BigInteger p2 = new BigInteger("11");

        int modp2 = one.mod(p2).intValue();
        BigInteger a2 = algorithm.getValueOfa(q2, p2);
        int check2 = a2.pow(q2.intValue()).intValue();

        Assert.assertEquals(modp2, check2);


        BigInteger q3 = new BigInteger("3");
        BigInteger p3 = new BigInteger("13");

        int modp3 = one.mod(p3).intValue();
        BigInteger a3 = algorithm.getValueOfa(q3, p3);
        int check3 = a3.pow(q3.intValue()).intValue();

        Assert.assertEquals(modp3, check3);

        BigInteger q4 = new BigInteger("2");
        BigInteger p4 = new BigInteger("73");

        int modp4 = one.mod(p4).intValue();
        BigInteger a4 = algorithm.getValueOfa(q4, p4);
        int check4 = a4.pow(q4.intValue()).intValue();

        Assert.assertEquals(modp4, check4);

        /*
         Disclaimer:
         Check of 'a' value is valid.
         But with these primes value of a is 1,
         so we shouldn't use it with the Schnnorr algorithm.
         */
    }


}