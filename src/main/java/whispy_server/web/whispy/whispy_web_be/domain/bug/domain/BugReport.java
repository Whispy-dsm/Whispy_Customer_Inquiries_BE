package whispy_server.web.whispy.whispy_web_be.domain.bug.domain;

import jakarta.persistence.*;
import lombok.*;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.enums.BugTopic;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_bug_report")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BugTopic bugTopic;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    public static BugReport create(String title, String content,BugTopic bugTopic, String email){
        return BugReport.builder()
                .title(title)
                .content(content)
                .bugTopic(bugTopic)
                .email(email)
                .build();
    }
}
