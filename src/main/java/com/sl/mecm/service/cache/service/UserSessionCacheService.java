package com.sl.mecm.service.web.service;

import com.alibaba.fastjson2.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class UserSessionCacheService {

    @Autowired
    private CacheManager cacheManager;

    public void saveInCache(String key, JSONObject sessionObject){
        Cache cache = cacheManager.getCache("userSessionCache");
        assert cache != null;
        cache.put(key, sessionObject);
    }
}
