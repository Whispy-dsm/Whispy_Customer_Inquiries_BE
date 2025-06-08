package whispy_server.web.whispy.whispy_web_be.domain.auth.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class RefreshTokenNotFoundException extends CustomException {
    public static final CustomException EXCEPTION= new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
