package whispy_server.web.whispy.whispy_web_be.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {
    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public void update(String newToken, Long newTtl){
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }
}
