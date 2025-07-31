package whispy_server.web.whispy.whispy_web_be.domain.chat.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;
import whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.request.ChatMessageRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.chat.service.admin.AdminSendService;
import whispy_server.web.whispy.whispy_web_be.domain.chat.service.admin.QueryChatRoomListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminSendService adminSendService;
    private final QueryChatRoomListService queryListChatRoomService;

    @MessageMapping(value = "/chat/send")
    public void message(
            @Payload ChatMessageRequestDto dto,
            MessageHeaders headers
    ){
        SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, SimpMessageHeaderAccessor.class);
        String targetSessionId = accessor.getFirstNativeHeader("GET_USER_SESSION");

        adminSendService.sendMessage(targetSessionId, dto);
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatRoom> getChatRoom(){
        return queryListChatRoomService.getChatRoom();
    }
}
