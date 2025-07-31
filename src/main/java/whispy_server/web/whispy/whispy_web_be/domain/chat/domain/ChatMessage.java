package whispy_server.web.whispy.whispy_web_be.domain.chat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.enums.Sender;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sender sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private LocalDateTime sentAt;

    @PrePersist
    private void prePersist(){
        this.sentAt = LocalDateTime.now();
    }

}
