package com.cts.redplastring.redplastringapplication.util;

import com.cts.redplastring.redplastringapplication.repository.EmployeeRepository;
import com.cts.redplastring.redplastringapplication.services.TokenService;
import com.cts.redplastring.redplastringapplication.model.Employee;
import com.cts.redplastring.redplastringapplication.model.TokenDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenGenerator{

    @Autowired
    TokenService tokenService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RedisTemplate<String, TokenDetails> redisTemplate;

    @Value("redsplastring")
    private String secret;

    private static final long JWT_TOKEN_VALIDITY = 48 * 60*60;

   // private static final long JWT_TOKEN_VALIDITY_ANDROID = 720 * 60 * 60; // this equals 1 days in seconds


    private HashOperations<String, String, TokenDetails> hashOperations;

    private static String SHORT_TOKEN_KEY = "redsplastring";

    public String generateToken( String email){

        String token = null;

        Employee employee = employeeRepository.findByEmail(email);

        Map<String, TokenDetails> values = tokenService.findAll();
        List<TokenDetails> tokenDetails = new ArrayList<TokenDetails>();
        for (Map.Entry<String, TokenDetails> entry : values.entrySet()) {
            if (entry.getValue().getUserId().equals(employee.getUserId())) {
                tokenDetails.add(entry.getValue());
            }
        }

        if (tokenDetails.size() < 1) {
            token = generateNewToken( employee.getUserId());

        } else {
            TokenDetails newToken = new TokenDetails();
            for (TokenDetails tokenDetail : tokenDetails) {
                if (tokenDetail.getToken().length() == 20 ) {
                    Date date = new Date();
                    long t = date.getTime();
                    Date expiryDate = new Date(
                            t + JWT_TOKEN_VALIDITY * 1000);

                    redisTemplate.opsForHash().delete(SHORT_TOKEN_KEY, tokenDetail.getToken());

                        token = Jwts.builder().setSubject(employee.getUserId() + System.currentTimeMillis())
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, secret)
                                .compact();
                        newToken.setToken(token);
                        newToken.setUserId(employee.getUserId());
                        newToken.setValidity(expiryDate);
                        redisTemplate.opsForHash().put(SHORT_TOKEN_KEY, token, newToken);
                        redisTemplate.expire(SHORT_TOKEN_KEY, 30, TimeUnit.DAYS);

                    }
                }
            }
            if (token == null) {
                token = generateNewToken(employee.getUserId());
            }
        return token;
        }

    public String generateNewToken( String userId){
        String token = null;

        TokenDetails tokenDetail = new TokenDetails();
        Date date = new Date();
        long t = date.getTime();
        Date expiryDate = new Date(
                t + JWT_TOKEN_VALIDITY * 1000);

            token = Jwts.builder().setSubject(userId + System.currentTimeMillis())
                    .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
            tokenDetail.setValidity(expiryDate);
            redisTemplate.expire(SHORT_TOKEN_KEY, 30, TimeUnit.DAYS);

        tokenDetail.setUserId(userId);
        tokenDetail.setToken(token);
        tokenDetail.setValidity(expiryDate);
        redisTemplate.opsForHash().put(SHORT_TOKEN_KEY, token, tokenDetail);
        return token;
    }

}
