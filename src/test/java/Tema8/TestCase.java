package Tema8;

abstract class TestCase {
    protected String name;

    public TestCase(String name) {
        this.name = name;
    }

    public abstract void runTest();
}
