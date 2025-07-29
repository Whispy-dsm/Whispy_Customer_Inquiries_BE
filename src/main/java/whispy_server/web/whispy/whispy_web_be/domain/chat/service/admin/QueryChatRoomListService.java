package whispy_server.web.whispy.whispy_web_be.domain.chat.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatRoom;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.repository.ChatRoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryChatRoomListService {
    private final ChatRoomRepository chatRoomRepository;
    private final AdminFacade adminFacade;

    @Transactional(readOnly = true)
    public List<ChatRoom> getChatRoom(){
        adminFacade.getCurrentAdmin();
        return chatRoomRepository.findByOrderByLastMessageAtDesc();
    }
}
