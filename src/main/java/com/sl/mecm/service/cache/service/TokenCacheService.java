package com.sl.mecm.service.cache.service;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenCacheService {

    @Autowired
    private Cache<String, String> authTokenCache;

    public String getSessionToken(String accessToken){
        Assert.hasText(accessToken, "for get - accessToken token must be not empty");
        return authTokenCache.get(accessToken);
    }

    public void saveSessionToken(String accessToken, String sessionToken){
        Assert.hasText(accessToken, "for save - accessToken token must be not empty");
        Assert.hasText(sessionToken, "for save - sessionToken token must be not empty");
        authTokenCache.put(accessToken, sessionToken);
    }
}
