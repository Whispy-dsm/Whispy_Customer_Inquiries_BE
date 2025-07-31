package whispy_server.web.whispy.whispy_web_be.domain.chat.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatMessage;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.enums.Sender;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository.ChatMessageRepository;
import whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.request.ChatMessageRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.response.ChatMessageResponseDto;
import whispy_server.web.whispy.whispy_web_be.domain.chat.facade.ChatRoomFacade;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminSendService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomFacade chatRoomFacade;
    private final AdminFacade adminFacade;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Transactional
    public void sendMessage(String session, ChatMessageRequestDto dto){
        adminFacade.getCurrentAdmin();

        ChatRoom chatRoom = chatRoomFacade.adminGetChatRoom(session);

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(Sender.ADMIN)
                .content(dto.content())
                .build();

        chatMessageRepository.save(chatMessage);

        simpMessagingTemplate.convertAndSend("/sub/chat/room" + chatRoom.getId(),
                ChatMessageResponseDto.from(chatMessage));
    }
}
