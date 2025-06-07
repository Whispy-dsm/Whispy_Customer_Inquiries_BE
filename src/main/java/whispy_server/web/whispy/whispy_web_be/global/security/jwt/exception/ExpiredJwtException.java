package whispy_server.web.whispy.whispy_web_be.global.security.jwt.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class ExpiredJwtException extends CustomException {
    public final static CustomException EXCEPTION = new ExpiredJwtException();

    private ExpiredJwtException(){
        super(ErrorCode.EXPIRED_JWT);
    }
}
