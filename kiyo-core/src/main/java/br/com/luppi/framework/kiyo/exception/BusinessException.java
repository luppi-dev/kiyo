package br.com.luppi.framework.kiyo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }
    public BusinessException(String message) {
        super(message);
    }
}
