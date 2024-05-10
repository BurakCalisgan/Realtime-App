package com.realtime.api.realtimeapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.realtime.api.realtimeapp.security.UserDetailsImpl;
import com.realtime.api.realtimeapp.util.props.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final JwtProperties jwtProperties;

    public String getUserNameFromJwtToken(String accessToken) {
        Map<String, Claim> claimMap = this.getClaimsAndValidate(accessToken);
        return claimMap.get("sub").asString();
    }
    public String getEmailFromJwtToken(String accessToken) {
        Map<String, Claim> claimMap = this.getClaimsAndValidate(accessToken);
        return claimMap.get("email").asString();
    }

    private Map<String, Claim> getClaimsAndValidate(String accessToken) {
        try {
            final DecodedJWT decodedJWT = JWT.decode(accessToken);
            final Algorithm algorithm = Algorithm.HMAC512(jwtProperties.getSecret());
            algorithm.verify(decodedJWT);
            if (decodedJWT.getExpiresAt().before(Calendar.getInstance().getTime())) {
                throw new RuntimeException("Invalid Token");
            }
            return decodedJWT.getClaims();
        } catch (Exception e) {
            log.error("Exception Occurred While Validating Token", e);
            throw new RuntimeException("Invalid Token");
        }
    }


    public boolean validateJwtToken(String accessToken) {
        try {
            final DecodedJWT decodedJWT = JWT.decode(accessToken);
            final Algorithm algorithm = Algorithm.HMAC512(jwtProperties.getSecret());
            algorithm.verify(decodedJWT);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT : {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenWithClaims(UserDetailsImpl userDetails) {
        Instant now = Instant.now();
        return JWT.create()
                .withClaim("email", userDetails.getEmail())
                .withClaim("userId", userDetails.getId())
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(now.plus(Duration.ofSeconds(jwtProperties.getExpirationMs())))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }
}