package pe.com.nttdata.account.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${config.endpoint.enterprise-customers}")
    private String url;

    @Bean
    public WebClient registrarWebClient() {
        return WebClient.create(url);
    }

}
