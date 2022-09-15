package edu.escuelaing.ieti.controller.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.dto.LoginDto;
import edu.escuelaing.ieti.dto.TokenDto;
import edu.escuelaing.ieti.entities.User;
import edu.escuelaing.ieti.exception.InvalidCredentialsException;
import edu.escuelaing.ieti.service.UserService;
import static edu.escuelaing.ieti.utils.Constants.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Value("${app.secret}")
    String secret;

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail());
        if (BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash())) {

            return generateTokenDto(user);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    private String generateToken(User user, Date expirationDate) {
        try {
            String jwt = Jwts.builder()
                    .setSubject(user.getId())
                    .claim(CLAIMS_ROLES_KEY, user.getRoles())
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
            return jwt;
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    private TokenDto generateTokenDto(User user) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES);
        String token = generateToken(user, expirationDate.getTime());
        return new TokenDto(token, expirationDate.getTime());
    }
}
