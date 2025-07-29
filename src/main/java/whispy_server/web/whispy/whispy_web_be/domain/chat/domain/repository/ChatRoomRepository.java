package whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySessionId(String sessionId);
    List<ChatRoom> findByOrderByLastMessageAtDesc(); //가장 최근에 메시지를 받은 채팅 방이 먼저 오도록 정렬
}
