package whispy_server.web.whispy.whispy_web_be.global.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import whispy_server.web.whispy.whispy_web_be.global.exception.facade.ExceptionFacade;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.CustomException;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorCode;
import whispy_server.web.whispy.whispy_web_be.global.exception.model.ErrorResponse;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final ExceptionFacade exceptionFacade;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (CustomException e){
            ErrorResponse errorResponse = exceptionFacade.resolveCustomException(e, request.getLocale());
            ErrorCode errorCode = e.getErrorCode();

            writeErrorResponse(response, errorCode.getStatus().value(), errorResponse);
        } catch (Exception e){
            ErrorResponse errorResponse = exceptionFacade.resolveException(e, request.getLocale());
            writeErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value(), errorResponse);
        }
    }

    private void writeErrorResponse(HttpServletResponse response, int statusCode, ErrorResponse errorResponse) throws IOException{
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
