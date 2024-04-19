package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Exercise;
import etf.unibl.org.ip.ipbackend.services.ApiNinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ApiNinjaServiceImpl implements ApiNinjaService {
    @Value("${api.ninja.key}")
    private String key;

    @Value("${api.ninja.url}")
    private String url;


    @Override
    public List<Exercise> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", key);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), List.class).getBody();
    }
}
