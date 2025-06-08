package whispy_server.web.whispy.whispy_web_be.domain.auth.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class InvalidRefreshTokenException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidRefreshTokenException();

    private InvalidRefreshTokenException(){
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
