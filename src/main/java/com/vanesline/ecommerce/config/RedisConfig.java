package com.vanesline.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import java.net.URISyntaxException;
import java.net.URI;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Value("${redis.url}")
    private String redisUrl;

    @Value("${redis.token}")
    private String redisToken;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() throws URISyntaxException {
        URI uri = new URI(redisUrl);

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(
                uri.getHost(),
                uri.getPort());

        config.setPassword(redisToken);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl() // vì dùng rediss://
                .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }
}
