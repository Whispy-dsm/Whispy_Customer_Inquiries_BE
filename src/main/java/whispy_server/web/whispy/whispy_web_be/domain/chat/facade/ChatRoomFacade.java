package whispy_server.web.whispy.whispy_web_be.domain.chat.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository.ChatRoomRepository;
import whispy_server.web.whispy.whispy_web_be.domain.chat.exception.ChatRoomNotFoundException;

@Component
@RequiredArgsConstructor
public class ChatRoomFacade {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom userGetOrCreateChatRoom(String sessionId){
        return chatRoomRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    ChatRoom chatRoom = ChatRoom.builder()
                            .sessionId(sessionId)
                            .build();
                    chatRoomRepository.save(chatRoom);
                    return chatRoom;
                });
    }

    public ChatRoom adminGetChatRoom(String sessionId){
        return chatRoomRepository.findBySessionId(sessionId)
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION); //TODO : 예외처리 추가
    }
}
