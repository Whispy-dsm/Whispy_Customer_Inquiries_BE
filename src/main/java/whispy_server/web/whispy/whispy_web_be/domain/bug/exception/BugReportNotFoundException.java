package whispy_server.web.whispy.whispy_web_be.domain.bug.exception;

import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;

public class BugReportNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new BugReportNotFoundException();

    private BugReportNotFoundException(){
        super(ErrorCode.BUG_REPORT_NOT_FOUND);
    }
}
