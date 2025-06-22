package whispy_server.web.whispy.whispy_web_be.domain.bug.service.user.event;

import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;

public record BugReportCreatedEvent(BugReport bugReport) { }
