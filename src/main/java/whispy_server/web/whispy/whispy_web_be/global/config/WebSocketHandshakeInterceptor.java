package whispy_server.web.whispy.whispy_web_be.global.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.UUID;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static final String SESSION_KEY = "USER_SESSION_ID";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            final HttpSession session = servletRequest.getServletRequest().getSession();

            String sessionId = (String) session.getAttribute(SESSION_KEY);
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString();
                session.setAttribute(SESSION_KEY, sessionId);
            }

            attributes.put(SESSION_KEY, sessionId);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // Optional: Logging or cleanup
    }
}
