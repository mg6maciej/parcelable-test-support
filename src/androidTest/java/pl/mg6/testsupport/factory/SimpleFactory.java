package pl.mg6.testsupport.factory;

import pl.mg6.testsupport.data.Simple;

public class SimpleFactory {

    public static Simple withZero() {
        return new Simple(0);
    }

    public static Simple withPerfectNumber() {
        return new Simple(6);
    }

    public static Simple withMaxValue() {
        return new Simple(Integer.MAX_VALUE);
    }
}
