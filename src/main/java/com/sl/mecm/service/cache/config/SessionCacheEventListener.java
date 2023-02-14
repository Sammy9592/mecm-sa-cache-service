package com.sl.mecm.service.cache.config;

import com.alibaba.fastjson2.JSONObject;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionCacheEventListener implements CacheEventListener<String, JSONObject> {

    @Override
    public void onEvent(CacheEvent<? extends String, ? extends JSONObject> cacheEvent) {
        log.info("Session Cache Event for:" + cacheEvent.getType().name()
                + ", key:[" + cacheEvent.getKey() + "]"
                + ", new value:[" + cacheEvent.getNewValue() + "]"
                + ", old value:[" + cacheEvent.getOldValue() + "]");
    }
}
