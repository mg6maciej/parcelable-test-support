package pl.mg6.testsupport;

import android.os.Parcelable;

public class ReparcelingResult<T extends Parcelable> {

    private final T original;
    private final T reparceled;
    private final boolean areEqual;
    private final String methodName;
    private final ReparcelingError error;

    ReparcelingResult(T original, T reparceled, boolean areEqual, String methodName, ReparcelingError error) {
        this.original = original;
        this.reparceled = reparceled;
        this.areEqual = areEqual;
        this.methodName = methodName;
        this.error = error;
    }

    public T getOriginal() {
        return original;
    }

    public T getReparceled() {
        return reparceled;
    }

    public boolean areEqual() {
        return areEqual;
    }

    public String getMethodName() {
        return methodName;
    }

    public ReparcelingError getError() {
        return error;
    }
}
