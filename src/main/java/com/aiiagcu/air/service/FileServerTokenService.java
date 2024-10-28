package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.token.IssueTokenRequest;
import com.aiiagcu.air.dto.token.IssueTokenResponse;
import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileServerTokenService {
    @Value("${aiiafs.internal.api.baseurl}")
    private String localApiBaseUrl;

    @Getter
    @Value("${aiiafs.public.api.baseurl}")
    private String publicApiBaseUrl;

    public List<String> issueToken(IssueTokenRequest token) {
        ArrayList<IssueTokenRequest> req = new ArrayList<>();
        req.add(token);
        return issueToken(req);
    }

    public List<String> issueToken(List<IssueTokenRequest> tokens) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();

        final String reqBody = gson.toJson(tokens);

        String url = localApiBaseUrl + "/token/issueMany";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        IssueTokenResponse[] responses = restTemplate.postForObject(url, new HttpEntity<>(reqBody, headers), IssueTokenResponse[].class);

        ArrayList<String> tokenStrings = new ArrayList<>();
        for (IssueTokenResponse response : responses) {
            if (response != null) {
                tokenStrings.add(response.getToken());
            }
        }

        return tokenStrings;
    }

    public boolean isPublicApiUrl(String url) throws URISyntaxException {
        String apiHost = new URI(publicApiBaseUrl).getHost();
        String testHost = new URI(url).getHost();

        return testHost.equals(apiHost);
    }

    public static String extractFileId(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String path = uri.getPath();

        String[] segments = path.split("/");

        return segments[segments.length - 1];
    }
}
