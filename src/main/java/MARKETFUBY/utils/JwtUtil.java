package MARKETFUBY.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    // token이 만료되면 true를 리턴
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    // token에서 userName을 가져와 리턴
    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("fubyId", String.class);
    }

    // token 생성 (createAccessToken과 createRefreshToken이 이 함수를 호출)
    public static String createToken (String fubyId, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();  // token 생성에 필요한 데이터를 담아두는 공간
        claims.put("fubyId", fubyId);  // 회원의 아이디(fubyId) 저장

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 발행 시각
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))  // 만료 시각
                .signWith(SignatureAlgorithm.HS256, key)  // HS256이라는 알고리즘과 주어진 key를 이용해 암호화
                .compact();
    }

    // AccessToken 생성
    public static String createAccessToken(String userName, String key, long expireTimeMs) {
        return createToken(userName, key, expireTimeMs);
    }

    // RefreshToken 생성
    public static String createRefreshToken (String userName, String key, long expireTimeMs) {
        return createToken(userName, key, expireTimeMs);
    }

    public static Claims parseRefreshToken(String value, String key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(value)
                .getBody();
    }
}
