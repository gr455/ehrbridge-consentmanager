package com.ehrbridge.consentmanager.helpers;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HTTPHelper {

    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatusCode status;

    public HTTPHelper() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }

    public String get(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String post(String url, String json) {
        System.out.println(json);
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        System.out.println(url);
        System.out.println(requestEntity);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println("edckjhj");
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public void put(String url, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.PUT, requestEntity, (Class<String>) null);
        this.setStatus(responseEntity.getStatusCode());
    }

    public void delete(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.DELETE, requestEntity, (Class<String>) null);
        this.setStatus((HttpStatus) responseEntity.getStatusCode());
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }
}