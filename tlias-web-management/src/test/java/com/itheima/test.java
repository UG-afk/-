package com.itheima;//package com.itheima;

import com.alibaba.fastjson.JSONObject;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    @Test
    public void test() {
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void test2() {
        Claims claims=Jwts.parser()
                        .setSigningKey("ithema")//指定签名秘钥
                        .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxODA3MzA4MX0.9R-u6xlnGHtGMrK9ffUMZ7zGJEmncJ36rysqq3fB6NU")
                        .getBody();

        System.out.println("这是name"+claims.get("name:", String.class));

    }
}
