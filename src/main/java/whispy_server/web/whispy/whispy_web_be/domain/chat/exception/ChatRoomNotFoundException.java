package whispy_server.web.whispy.whispy_web_be.domain.chat.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class ChatRoomNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ChatRoomNotFoundException();
    private ChatRoomNotFoundException(){
        super(ErrorCode.CHAT_ROOM_NOT_FOUND);
    }
}
