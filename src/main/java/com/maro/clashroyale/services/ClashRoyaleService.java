package com.maro.clashroyale.services;

import com.maro.clashroyale.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class ClashRoyaleService {

    protected RequestEntity buildRequestEntity(String uriTail, Object... objects) {
        String url = Constants.BASE_URL + String.format(uriTail,objects);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", Constants.AUTHORIZATION);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        RequestEntity requestEntity = null;
        try {
            requestEntity = RequestEntity.get(new URI(url)).headers(headers).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return requestEntity;
    }
}
