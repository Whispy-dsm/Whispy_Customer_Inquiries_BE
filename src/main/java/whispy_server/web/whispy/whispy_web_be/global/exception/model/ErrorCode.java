package whispy_server.web.whispy.whispy_web_be.global.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal_server"),

    //user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "error.user_not_found"),
    USER_EXISTS(HttpStatus.CONFLICT, "error.user_exists"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "error.password_mismatch"),

    //jwt
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "error.expired_jwt"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "error.invalid_jwt"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "error.invalid_refresh_token"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "error.refresh_token_not_found"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "error.validation"),

    //bug-report
    BUG_REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "error.bug_report_not_found"),

    //email
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "error.email_send_failed"),

    DISCORD_WEBHOOK_FAILED(HttpStatus.BAD_GATEWAY, "error.discord_webhook_failed"),

    //CHAT
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "error.chat_room_not_found");

    private final HttpStatus status;
    private final String messageKey;
}
