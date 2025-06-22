package whispy_server.web.whispy.whispy_web_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class WhispyWebBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhispyWebBeApplication.class, args);
    }

}
