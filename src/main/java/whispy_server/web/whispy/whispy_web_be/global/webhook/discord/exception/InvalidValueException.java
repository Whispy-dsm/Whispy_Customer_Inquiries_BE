package whispy_server.web.whispy.whispy_web_be.global.webhook.discord.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class InvalidValueException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidValueException();

    private InvalidValueException(){
        super(ErrorCode.DISCORD_WEBHOOK_FAILED);
    }
}
