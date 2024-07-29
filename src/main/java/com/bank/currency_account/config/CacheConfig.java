package com.bank.currency_account.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(exchangeRatesCache());
        return cacheManager;
    }

    Caffeine<Object, Object> exchangeRatesCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS);
    }
}
