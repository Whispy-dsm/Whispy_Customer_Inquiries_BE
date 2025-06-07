package whispy_server.web.whispy.whispy_web_be.global.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(
        int status,
        String code,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp,
        String exception,
        List<FieldValidationError> fieldErrors
){
    public record FieldValidationError(String field, String message){}

    public static ErrorResponse of(ErrorCode errorCode, String message, Exception e){
        return ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .code(errorCode.name())
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(e.getClass().getSimpleName())
                .fieldErrors(List.of())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message, Exception e, List<FieldValidationError> errors){
        return ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .code(errorCode.name())
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(e.getClass().getSimpleName())
                .fieldErrors(errors)
                .build();
    }
}