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
        //TO DO
    }

    @Test
    public void getPrivateKeyTest(){
        int length = 2;

        BigInteger q1 = BigInteger.valueOf(7);
        BigInteger privateKey1 = algorithm.getPrivateKey(length, q1);
        Assert.assertTrue(privateKey1.compareTo(q1) == -1);

        BigInteger q2 = BigInteger.valueOf(11);
        BigInteger privateKey2 = algorithm.getPrivateKey(length, q2);
        Assert.assertTrue(privateKey2.compareTo(q2) == -1);

        BigInteger q3 = BigInteger.valueOf(13);
        BigInteger privateKey3 = algorithm.getPrivateKey(length, q3);
        Assert.assertTrue(privateKey3.compareTo(q3) == -1);
    }

    @Test
    public void getPublicKeyTest(){
        // TO DO
    }


}