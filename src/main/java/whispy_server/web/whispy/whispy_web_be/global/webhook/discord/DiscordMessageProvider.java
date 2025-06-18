package whispy_server.web.whispy.whispy_web_be.global.webhook.discord;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.global.webhook.discord.exception.InvalidValueException;

@Component
@RequiredArgsConstructor
public class DiscordMessageProvider {
    private final DiscordFeignClient discordFeignClient;

    public void sendMessage(DiscordMessage discordMessage){
        try{
            discordFeignClient.sendMessage(discordMessage);
        }catch (FeignException e){
            throw InvalidValueException.EXCEPTION;
        }
    }
}
