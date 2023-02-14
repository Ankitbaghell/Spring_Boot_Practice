package com.hexaview.trs.userservice.util;

import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.model.User;
import constant.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60;

  /**
   * @param token this method username from token
   * @return username
   */
  public String getUsernameFromToken(String token) {
    String username = null;
    try {
      username = getClaimFromToken(token, Claims::getSubject);
    } catch (MalformedJwtException e) {
      username = TokenConstants.INVALID_TOKEN;
    }
    return username;
  }

  /**
   * @param token this method extract expiration from token using claims
   * @return Date
   */
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public String getUserRoleFromToken(String token) {
    Claims claims = getAllClaimsFromToken(token);
    var role = (String) claims.get("Roles");
    return role;
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  /**
   * @param token this method extracts claims set of rules define to help to extract info from token
   *     using secret key
   * @return Claims
   */
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(TokenConstants.SECRET_KEY).parseClaimsJws(token).getBody();
  }

  /**
   * @param token check expiration of token
   * @return boolean value
   */
  public Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  /**
   * this method generate token
   *
   * @param user
   * @return Token
   */
  public Token generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    String roleName = "";
    String token = doGenerateToken(user.getEmail());
    return new Token.Builder(token, user.getId(), TokenConstants.TOKEN_TYPE).build();
  }

  /**
   * @param
   * @param subject this is internal method calling to generate token using secretkey
   * @return token
   */
  private String doGenerateToken(String subject) {

    return Jwts.builder()
        .claim("Roles", "")
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, TokenConstants.SECRET_KEY)
        .compact();
  }
}
