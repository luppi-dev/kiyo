package br.com.luppi.framework.kiyo.exception;

import br.com.luppi.framework.kiyo.domain.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.ReflectPermission;
import java.util.stream.Collectors;

@RestControllerAdvice
public class KiyoHandlerException {

    private static final String DELIMITER = ", ";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(
                ErrorResponseDTO
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception
                        .getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(DELIMITER)))
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final ConstraintViolationException exception) {
        return new ResponseEntity<>(
                ErrorResponseDTO
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception
                        .getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(DELIMITER)))
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final BusinessException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.basicError(HttpStatus.BAD_REQUEST, e),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final ApplicationException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(
                ErrorResponseDTO.basicError(status, e),
                status
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handler(final Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .status(status.value())
                        .message("Internal error")
                        .build(),
                status
        );
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final AuthorizationException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .status(HttpStatus.FORBIDDEN.value())
                        .message(e.getMessage())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final AuthenticationException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final NotFoundException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TeaPotException.class)
    public ResponseEntity<ErrorResponseDTO> handler(final TeaPotException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .status(HttpStatus.I_AM_A_TEAPOT.value())
                        .message(e.getMessage())
                        .build(),
                HttpStatus.I_AM_A_TEAPOT
        );
    }
}
