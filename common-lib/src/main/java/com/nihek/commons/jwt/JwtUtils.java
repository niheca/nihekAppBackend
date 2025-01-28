package com.nihek.commons.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.jfr.Enabled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private String secretToken = "dbgljbvbaoebvbsZkvbpsabvpibdclkvbdspifvahbcdisvfwvfouyewvbalfibeslfvafbcldasbpfiewaubfbeluafbeafbkjlsbafiaew";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretToken)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }
    }

    public Integer extractedUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }
        catch (Exception e){
            return null;
        }
    }
}
