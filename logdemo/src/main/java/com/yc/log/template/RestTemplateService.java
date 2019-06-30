package com.yc.log.template;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * 远程调用 get
     * @param url
     * @param hearders
     * @param type
     * @return
     */
    public <T> T get(String url, Map<String, Object> headers, Class<T> type) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(headers).encode();
        String uri = uriComponents.toUriString();
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeader.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.entrySet().forEach(b -> {
            httpHeader.set(b.getKey(), String.valueOf(b.getValue()));
        });
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeader);
        try {
            logger.info("remote uri = {}",uri);
            ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.GET, entity, type);
            int code = response.getStatusCodeValue();
            if(code == 200 || code == 201 || code == 202)
                return response.getBody();
            logger.info("remote get method request fail");
            return null;
        }catch(Exception e) {
            logger.error("remote get method request fail {}",e);
            return null;
        }
    }
    
    public <T> T post(String url, Map<String, Object> headers, String body, Class<T> type) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(headers).encode();
        String uri = uriComponents.toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.entrySet().forEach(b -> {
            httpHeaders.set(b.getKey(), String.valueOf(b.getValue()));
        });
        HttpEntity<String> entity = new HttpEntity<String>(body, httpHeaders);
        try {
            ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.POST, entity, type);
            int code = response.getStatusCodeValue();
            if(code == 200 || code == 201 || code == 202)
                return response.getBody();
            logger.info("remote method post request fail");
            return null;
        }catch(Exception e) {
            logger.error("remote method post request fail {}",e);
            return null;
        }
    }
}
