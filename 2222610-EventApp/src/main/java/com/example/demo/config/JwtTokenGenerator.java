package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts; // Add the missing import
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenGenerator extends OncePerRequestFilter {

    //create a variable for logger
    //annotate with @Slf4j to get the instance of logger
    private static final Logger log = LoggerFactory.getLogger(JwtTokenGenerator.class);

    //create a variable to store validtime which is from application.properties
    //annotate with @value to get the value from application properties
    @Value("${jwt.validTime}")
    private long validTime;

    //annotate with @value to get the value from application properties
    @Value("${jwt.secret}")
    private String secret;
    //annotate with @value to get the value from application properties
    @Value("${jwt.issuer}")
    private String issuer;

    //annotate with @value to get the value from application properties
    @Value("${jwt.keytype}")
    private String keyType;

    //annotate with @value to get the value from application properties
    @Value("${jwt.eventId}")
    private String eventId;

    //make use of User Service to get the user details
    //annotate with @Autowired to get the instance of UserService
    @Autowired
    private UserService userService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        // Add your implementation here
        //get the token from the request header
        String token = request.getHeader("Authorization");
        //check if the token is not null and starts with "Bearer"
        if(token != null && token.startsWith("Bearer")){
            //remove the "Bearer" from the token
                        token = token.substring(7).trim();
                        //create a new instance of JwtToken
                        Jwt<?, ?> jwtToken = Jwts.parser().setSigningKey(secret).parse(token);
                        //create claim  object from jwtToken
                        Claims claims = (Claims) jwtToken.getBody();
                        //get the expiration time from the claim and check if difference between current time and expiration time is less than 01 minute
                        // ...
                        try {
                            if (claims.getExpiration().getTime() - System.currentTimeMillis() < 60000) {
                                if (claims.getIssuer().equals(issuer) && claims.get("keytype", keyType.getClass()).equals(keyType)) {
                                    String userEmail = claims.get("emailId", String.class);
                                    User user = (User) userService.getUserByEmailId(userEmail).getResp(); // Cast the result to User
                                    if (user != null) {
                                        filterChain.doFilter(request, response);
                                    }
                                     else {
                                        logger.info("User is not authenticated");
                                        response.sendError(401, "User is not authenticated");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            //print the exception
                            logger.error(e.getMessage());
                            e.printStackTrace();
                        }
        }
    }

    //implement method to create jwt token
    public String createJwtToken(User user) {
        // Add your implementation here
        //create a new instance of JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder();
        //set the issuer
        jwtBuilder.setIssuer(issuer);
        //set the subject
        jwtBuilder.setSubject(user.getEmailid());
        //set the expiration time
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + validTime));
        //set the keytype
        jwtBuilder.claim("keytype", keyType);
        //set the eventId
        jwtBuilder.claim("eventId", eventId);
        //set the emailId
        jwtBuilder.claim("emailId", user.getEmailid());
        //sign the token
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secret);
        //return the token
        log.info(ALREADY_FILTERED_SUFFIX);
        return jwtBuilder.compact();
    }
}
