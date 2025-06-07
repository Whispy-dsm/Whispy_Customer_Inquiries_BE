package whispy_server.web.whispy.whispy_web_be.global.exception.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorResponse;

import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ExceptionFacade {
    private final MessageSource messageSource;

    public ErrorResponse resolveCustomException(CustomException e, Locale locale){
        ErrorCode errorCode = e.getErrorCode();
        String message = messageSource.getMessage(errorCode.getMessageKey(), null, locale);
        return ErrorResponse.of(errorCode, message, e);
    }

    public ErrorResponse resolveBindException(BindException e, Locale locale){
        List<ErrorResponse.FieldValidationError> errors= e.getFieldErrors().stream()
                .map(fieldError -> new ErrorResponse.FieldValidationError(
                        fieldError.getField(),
                        messageSource.getMessage(fieldError, locale)
                )).toList();
        String message = messageSource.getMessage(ErrorCode.VALIDATION_ERROR.getMessageKey(), null, locale);
        return ErrorResponse.of(ErrorCode.VALIDATION_ERROR, message, e, errors);
    }

    public ErrorResponse resolveException(Exception e, Locale locale){
        String message = messageSource.getMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessageKey(), null, locale);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, message, e);
    }
}
