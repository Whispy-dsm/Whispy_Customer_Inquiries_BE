package whispy_server.web.whispy.whispy_web_be.domain.admin.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class UserExistException extends CustomException {
    public final static CustomException EXCEPTION = new UserExistException();

    private UserExistException(){
        super(ErrorCode.USER_EXISTS);
    }
}
