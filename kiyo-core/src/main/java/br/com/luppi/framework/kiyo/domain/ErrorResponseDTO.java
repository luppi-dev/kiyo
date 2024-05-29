package br.com.luppi.framework.kiyo.domain;

import br.com.luppi.framework.kiyo.exception.AbstractException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
    private Integer status;
    private String error;
    private String message;

    public static ErrorResponseDTO basicError(HttpStatus httpStatus, AbstractException e) {
        return ErrorResponseDTO.builder()
                .status(httpStatus.value())
                .message(e.getMessage())
                .build();
    }
}
