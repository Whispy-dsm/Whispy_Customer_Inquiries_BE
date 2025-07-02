package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.event;

import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;

public record BugAnsweredEvent(
        BugReport bugReport,
        String answer
) { }
