package com.yc.rpc.client;

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
    
    public <T> T post(String url, Map<String,Object> headers,Class<T> type){
        UriComponents uriComponent = UriComponentsBuilder.fromUriString(url).build().expand(headers).encode();
        String uri = uriComponent.toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        String str = "{\"content\":{\"productModel\":\"itel W6002\",\"deviceTag\":\"HD9XZFA3\",\"gaid\":\"640cd061-43b7-47e1-b567-4b27c726fd61\",\"imei\":\"359229106181540\",\"lock\":{\"expiration\":7959743999,\"state\":\"active\"},\"isRooted\":false,\"manufacturer\":\"itel\"}}";
        headers.entrySet().stream().forEach(b -> {
            httpHeaders.set(b.getKey(), String.valueOf(b.getValue()));
        });
        HttpEntity<String> entity = new HttpEntity<>(str, httpHeaders);
        try{
            ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.POST, entity, type);
            int code = response.getStatusCodeValue();
            if(code == 200 || code == 201 || code == 202){
                logger.info("remote get request success");
                return response.getBody();
            }
        }catch(Exception e){
            logger.error("remote get request fail {}",e);
            return null;
        }
        logger.info("remote get request fail");
        return null;
    }
}
