package com.aiiagcu.air.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.port}")
    private int port;

    //Lettuce(Redis Java Client) 세팅 Bean 생성
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        //Redis 설정 Instance 생성
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        //Redis 비밀번호 설정
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    //Redis 내부 세팅 Bean 생성
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        //Serializer 생성
        StringRedisSerializer serializer = new StringRedisSerializer();
        //RedisTemplate (설정용 Instance)
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        //연결할 컨테이너 지정
        template.setConnectionFactory(lettuceConnectionFactory());
        //직렬화할 때 사용할 방법 지정
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

}
