package pl.mg6.testsupport;

public final class ReparcelingError extends RuntimeException {

    public ReparcelingError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
