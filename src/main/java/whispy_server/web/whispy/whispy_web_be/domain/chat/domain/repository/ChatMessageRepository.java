package whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom_IdOrderBySentAt(Long roomId);
}
