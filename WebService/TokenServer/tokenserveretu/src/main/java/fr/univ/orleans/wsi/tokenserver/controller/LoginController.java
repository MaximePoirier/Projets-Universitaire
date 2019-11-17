package fr.univ.orleans.wsi.tokenserver.controller;

import io.jsonwebtoken.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static services.Constantes.AUTHORIZATION;

@RestController
public class LoginController {

    private static final String SECRET_KEY = "securite";
    private static final int EXPIRATION_TIME =  3600*1000;

    private static Map<String, String> loginPassword = new HashMap<>();
    private static Map<String, List<String>> loginRoles = new HashMap<>();
    static {
        loginPassword.put("fred","abcd");
        loginRoles.put("fred", Arrays.asList("user","admin","supersuperadmin"));
        loginPassword.put("yo","yo");
        loginRoles.put("yo", Arrays.asList("user", "scrummaster"));
        loginPassword.put("math","math");
        loginRoles.put("math", Arrays.asList("admin","ceinturenoire"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login,
                                       @RequestParam String password) {
        if(loginPassword.containsKey(login)){
            if(loginPassword.get(login).contains(password)){
                Claims claims = Jwts.claims().setSubject(login);
                claims.put("roles", loginRoles.get(login));
                String token = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes()).compact();
                return ResponseEntity.status(HttpStatus.ACCEPTED).header(AUTHORIZATION,token).body("Vous êtes connectés");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mot de passe incorrect");
            }

        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login inexistant");
        }
    }

    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken(@RequestHeader(AUTHORIZATION)String token) {
        try{
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).header(AUTHORIZATION,token).body("Utilisateur Connecté");
        }
        catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Haha bien essayé !");
        }
    }
}
