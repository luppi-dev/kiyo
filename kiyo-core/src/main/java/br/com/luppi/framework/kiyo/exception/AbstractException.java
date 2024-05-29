package br.com.luppi.framework.kiyo.exception;

import java.io.Serial;

public abstract class AbstractException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AbstractException() {
        super();
    }
    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }
    public AbstractException(String message) {
        super(message);
    }
    public AbstractException(Throwable cause) {
        super(cause);
    }
}
