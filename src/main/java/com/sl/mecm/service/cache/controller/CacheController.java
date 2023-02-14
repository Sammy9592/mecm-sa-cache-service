package com.sl.mecm.service.web.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.sl.mecm.service.web.service.UserSessionCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    private UserSessionCacheService userSessionCacheService;

    @RequestMapping(value = "/session/save", method = RequestMethod.POST)
    public Mono<ResponseEntity<JSONObject>> retrieveSession(@RequestBody String body){
        return Mono.just(body)
                .doOnNext(theBody -> log.info("receive request:" + theBody))
                .map(JSON::parseObject)
                .map(bodyObject -> {
                    String key = bodyObject.getString("dataKey");
                    JSONObject data = bodyObject.getJSONObject("dataObject");
                    userSessionCacheService.saveInCache(key, data);
                    return new ResponseEntity<>(JSONObject.of("code", "200", "message", "success"), HttpStatus.OK);
                })
                .onErrorResume(throwable -> {
                    log.error("error to save cache: " + throwable.getLocalizedMessage(), throwable);
                    return Mono.just(new ResponseEntity<>(JSONObject.of("code", "503", "message", throwable.getLocalizedMessage()), HttpStatus.OK));
                });
    }
}
