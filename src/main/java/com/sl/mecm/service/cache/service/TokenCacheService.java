package com.sl.mecm.service.cache.service;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenService {

    @Autowired
    private Cache<String, String> authTokenCache;

    public String getSessionToken(String authToken, String accessToken){
        Assert.hasText(authToken, "for get - auth token must be not empty");
        Assert.hasText(accessToken, "for get - accessToken token must be not empty");
        return authTokenCache.get(buildTokenId(authToken, accessToken));
    }

    public void saveSessionToken(String authToken, String accessToken, String sessionToken){
        Assert.hasText(authToken, "for save - auth token must be not empty");
        Assert.hasText(accessToken, "for save - accessToken token must be not empty");
        Assert.hasText(sessionToken, "for save - sessionToken token must be not empty");
        authTokenCache.put(buildTokenId(authToken, accessToken), sessionToken);
    }

    private String buildTokenId(String authToken, String accessToken){
        String encodeString = authToken + "." + accessToken;
        return Base64.getEncoder().encodeToString(encodeString.getBytes(StandardCharsets.UTF_8));
    }
}
