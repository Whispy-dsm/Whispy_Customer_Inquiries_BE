package whispy_server.web.whispy.whispy_web_be.global.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import whispy_server.web.whispy.whispy_web_be.global.exception.facade.ExceptionFacade;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorResponse;

import org.springframework.validation.BindException;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ExceptionFacade exceptionFacade;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> CustomExceptionHandling(CustomException e, Locale locale){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(exceptionFacade.resolveCustomException(e, locale));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> BindExceptionHandling(BindException e, Locale locale){
        return ResponseEntity.badRequest()
                .body(exceptionFacade.resolveBindException(e, locale));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> ExceptionHandling(Exception e, Locale locale){
        return ResponseEntity.internalServerError()
                .body(exceptionFacade.resolveException(e, locale));
    }
}
