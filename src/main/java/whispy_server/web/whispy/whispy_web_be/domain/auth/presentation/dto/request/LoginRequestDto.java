package whispy_server.web.whispy.whispy_web_be.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
    @Email
    @NotBlank
    String email,

    @NotBlank
    String password
) { }
