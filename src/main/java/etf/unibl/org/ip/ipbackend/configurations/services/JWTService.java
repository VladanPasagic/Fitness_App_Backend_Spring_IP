package etf.unibl.org.ip.ipbackend.configurations.services;

import etf.unibl.org.ip.ipbackend.configurations.models.JWTUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long tokenExpirationTime;

    @Value("${jwt.issuer}")
    private String tokenIssuer;

    public String generateToken(JWTUser user) {
        Map<String, Object> extraTokenClaims = new HashMap<>();
        extraTokenClaims.put("role", user.getRole());
        return generateToken(extraTokenClaims, user);
    }

    private String generateToken(Map<String, Object> extraClaims, JWTUser user) {
        Date currDate = new Date(System.currentTimeMillis());
        Date expDate = new Date(System.currentTimeMillis() + tokenExpirationTime);
        Key key = getSigningKey();
        return Jwts.builder().subject(user.getUsername()).issuer(tokenIssuer).issuedAt(currDate).expiration(expDate).claims(extraClaims).signWith(key).compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
