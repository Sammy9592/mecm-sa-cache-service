package com.sl.mecm.service.cache.service;

import com.alibaba.fastjson2.JSONObject;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionCacheService {

    @Autowired
    private Cache<String, JSONObject> sessionCache;

    public JSONObject queryInCache(String key){
        return sessionCache.get(key);
    }

    public void saveInCache(String key, JSONObject sessionObject){
        sessionCache.put(key, sessionObject);
    }
}