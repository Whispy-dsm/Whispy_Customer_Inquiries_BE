package whispy_server.web.whispy.whispy_web_be.global.email.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class EmailSendFailedException extends CustomException {
    public static final CustomException EXCEPTION = new EmailSendFailedException();

    private EmailSendFailedException(){
        super(ErrorCode.EMAIL_SEND_FAILED);
    }
}
