package Tema8;

public class TestRunner {
    public static void main(String[] args) {

        TestCase[] tests = {
                new LoginTest(),
                new SearchTest()
        };

        for (TestCase test : tests) {
            test.runTest();
            System.out.println("------------------");
        }
    }
}
