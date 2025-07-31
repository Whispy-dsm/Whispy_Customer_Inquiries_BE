package whispy_server.web.whispy.whispy_web_be.domain.chat.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.request.ChatMessageRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.chat.service.user.UserSendService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserController {
    private final UserSendService userSendService;

    @MessageMapping(value = "/chat/send")
    public void message(
            @Payload ChatMessageRequestDto dto,
            MessageHeaders headers
    ){
        SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, SimpMessageHeaderAccessor.class);
        String sessionId = Objects.requireNonNull(accessor.getSessionAttributes()).get("USER_SESSION_ID").toString();
        userSendService.sendMessage(sessionId, dto);
    }

}
