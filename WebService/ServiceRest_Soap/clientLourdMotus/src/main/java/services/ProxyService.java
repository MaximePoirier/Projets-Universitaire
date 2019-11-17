package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import modele.Pseudo;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import services.exceptions.BadTokenException;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.LOCATION;

public class ProxyService {

    public static String connexion(Pseudo pseudo, String token) throws BadTokenException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(pseudo);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(jsonInString, httpHeaders);
        try {
            ResponseEntity<String> resultat = restTemplate.postForEntity("http://localhost:8080/motus/partie", httpEntity, String.class);
            return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }

    public static String demmarer(String dico, String pseudo, String token) throws  BadTokenException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.put("dico", Collections.singletonList(dico));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        try {
            ResponseEntity<String> resultat = restTemplate.postForEntity("http://localhost:8080/motus/partie/" + pseudo, httpEntity, String.class);
            return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }


    public static String getDico(String token) throws  BadTokenException{
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> resultat = null;
        try {
            resultat = restTemplate.exchange("http://localhost:8080/motus/dico", HttpMethod.GET, httpEntity, String.class);
            return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }

    public static String essaie(String mot, String pseudo, String token) throws BadTokenException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.put("mot", Arrays.asList(mot));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> resultat = null;
        try {
            resultat = restTemplate.exchange("http://localhost:8080/motus/partie/" + pseudo, HttpMethod.PUT, httpEntity, String.class);
            if (resultat.getBody().contains("XXXXXXX")) {
                return resultat.getBody().concat("     Félicitation vous avez gagner taper exit pour quitter :");
            } else return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }

    public static String getEssaies(String pseudo, String token) throws BadTokenException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> resultat = null;
        try {
            resultat = restTemplate.exchange("http://localhost:8080/motus/partie/" + pseudo + "/essaies", HttpMethod.GET, httpEntity, String.class);
            return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }

    public static String deconnecter(String pseudo, String token) throws BadTokenException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> resultat = null;
        try {
            resultat = restTemplate.exchange("http://localhost:8080/motus/partie/" + pseudo, HttpMethod.DELETE, httpEntity, String.class);
            return resultat.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() != HttpStatus.OK.value()) {
                if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                    return e.getResponseBodyAsString();
                }

                throw new BadTokenException(e.getResponseHeaders().get(LOCATION).get(0));
            }
            return e.getResponseBodyAsString();
        }
    }

    public static String getToken(String login, String password, String authUri){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.put("login", Arrays.asList(login));
        map.put("password", Arrays.asList(password));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> resultat = null;
        try {
            resultat = restTemplate.postForEntity(authUri, httpEntity, String.class);
            HttpHeaders headers = resultat.getHeaders();
            return headers.get(AUTHORIZATION).get(0);
        } catch (HttpClientErrorException e){

        }
        return null;
    }
}