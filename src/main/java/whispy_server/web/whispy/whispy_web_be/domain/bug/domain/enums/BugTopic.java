package whispy_server.web.whispy.whispy_web_be.domain.bug.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BugTopic {

    UI("화면 문제"),
    FUNCTIONAL("기능 문제"),
    PERFORMANCE("성능 관련"),
    SECURITY("보안 이슈"),
    OTHER("기타");

    private final String description;

    @Override
    public String toString() {
        return description;
    }
}