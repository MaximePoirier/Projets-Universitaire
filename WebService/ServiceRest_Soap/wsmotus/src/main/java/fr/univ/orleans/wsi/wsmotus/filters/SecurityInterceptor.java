package fr.univ.orleans.wsi.wsmotus.filters;


import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class SecurityInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION,httpServletRequest.getHeader(AUTHORIZATION));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String>  resultat = null;
        try{
            resultat = restTemplate.exchange("http://localhost:8000/checkToken", HttpMethod.GET, httpEntity, String.class);

        }catch (Exception e){
            httpServletResponse.setHeader("location", "http://localhost:8000/login");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;

        }
        finally {
            if (resultat.getStatusCode() == HttpStatus.ACCEPTED) {
                return true;
            }
            else return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}