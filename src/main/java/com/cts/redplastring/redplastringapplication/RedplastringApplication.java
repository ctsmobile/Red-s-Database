package com.cts.redplastring.redplastringapplication;

import com.cts.redplastring.redplastringapplication.model.TokenDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.cts.redplastring.redplastringapplication"})
@EnableJpaRepositories({"com.cts.redplastring.redplastringapplication.repository"})
@ComponentScan({"com.cts.redplastring.redplastringapplication"})
@EnableScheduling
//@EntityScan({ "com.cts.redplastring.redplastringapplication", "com.cts.redplastring.redplastringapplication.Services" })
public class RedplastringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedplastringApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, TokenDetails> redisTemplate() {
		RedisTemplate<String, TokenDetails> redisTemplate = new RedisTemplate<String, TokenDetails>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		 return new BCryptPasswordEncoder();
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

//	@Bean
//	BCryptPasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
	@Bean
    ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
