package whispy_server.web.whispy.whispy_web_be.domain.chat.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository.ChatRoomRepository;

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
                .orElseThrow(RuntimeException::new); //TODO : 예외처리 추가
    }
}
