package user_management_microservces.User_management.Service_Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.function.Function;

@Service
public class Jwt_Validator {
    @Value("${jwt.secret}")
    public String secretkey;


    private SecretKey getkey() {
        byte[] key= Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractData(String token){
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private<T> T extractOneClaim(String token, Function<Claims,T> resolver){
        Claims claims=this.extractData(token);
        return resolver.apply(claims);

    }
    public String extractSubject(String token){
        return this.extractOneClaim(token,Claims::getSubject);
    }
    public boolean validateToken(String token,String username){
        String tokenusername=this.extractSubject(token);
        return (!this.isExpired(token)&& username.equals(tokenusername));
    }
    private boolean isExpired(String token){
        return (this.extractExpiration(token).before(new Date()));
    }
    private Date extractExpiration(String token){
        return this.extractOneClaim(token,Claims::getExpiration);
    }




}
