package support.verification;

public class Verifier {

    public static void verifyEqual(String actualResult, String expectedResult){
        if(!actualResult.equals(expectedResult)){
            throw new AssertionError("[ERROR] Expected is [" + expectedResult + "] but found [" + actualResult + "]");
        }
    }
}
