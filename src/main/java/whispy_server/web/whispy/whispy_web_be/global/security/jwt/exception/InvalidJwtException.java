package whispy_server.web.whispy.whispy_web_be.global.security.jwt.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class InvalidJwtException extends CustomException {
    public final static CustomException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException(){
        super(ErrorCode.INVALID_JWT);
    }
}
