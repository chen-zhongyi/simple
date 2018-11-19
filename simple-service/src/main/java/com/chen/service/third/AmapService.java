package com.chen.service.third;

import com.chen.results.AmapResult;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AmapService {

    private static final String KEY = "b3d20a70300b0f95dfb893cfd314afb6";

    private final RestTemplate restTemplate;

    public AmapService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AmapResult districtList() {
        String url = "https://restapi.amap.com/v3/config/district?key={1}&subdistrict={2}";
        return restTemplate.getForObject(url, AmapResult.class, KEY, 3);
    }

}
