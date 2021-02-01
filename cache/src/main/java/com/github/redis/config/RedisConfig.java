package com.github.redis.config;

import cn.hutool.core.util.StrUtil;
import com.github.redis.locks.DistributedLock;
import com.github.redis.locks.RedisDistributedLock;
import com.github.redis.locks.RedissonDistributedLock;
import com.github.redis.properies.RedissonProperties;
import com.github.redis.repository.SuperBaseRedisOps;
import com.github.redis.repository.impl.RedisOpsImpl;
import com.github.redis.utils.RedisObjectSerializer;
import com.github.redis.utils.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static cn.hutool.core.util.StrUtil.COLON;

/**
 * @author jie
 */
@EnableCaching
@Configuration
@ConditionalOnClass(RedisConnectionFactory.class)
@EnableConfigurationProperties({RedisProperties.class, RedissonProperties.class})
public class RedisConfig {

    /**
     * key 的生成策略
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(COLON);
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }

    /**
     * 用于 @Cacheable 相关注解
     *
     * @param redisConnectionFactory 链接工厂
     * @return 缓存管理器
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defConfig = getDefConf();
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defConfig)
                .build();
    }

    private RedisCacheConfiguration getDefConf() {
        RedisCacheConfiguration def = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new RedisObjectSerializer()));
        def.entryTtl(Duration.ofDays(1));
        def.computePrefixWith(cacheName -> cacheName.concat(COLON));
        return def;
    }


    /**
     * RedisTemplate配置
     *
     * @param factory redis链接工厂
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        setSerializer(factory, template);
        return template;
    }

    private void setSerializer(RedisConnectionFactory factory, RedisTemplate template) {
        RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(redisObjectSerializer);
        template.setValueSerializer(redisObjectSerializer);
        template.setConnectionFactory(factory);
    }

    /**
     * stringRedisTemplate 配置
     *
     * @param factory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        setSerializer(factory, template);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisUtils getRedisOps(RedisTemplate redisTemplate) {
        return new RedisUtils(redisTemplate, true);
    }

    @Bean
    @ConditionalOnMissingBean
    public SuperBaseRedisOps redisPlusOps(RedisUtils redisUtils) {
        return new RedisOpsImpl(redisUtils);
    }

    /**
     * 分布式锁(自己实现的)
     *
     * @param redisTemplate redis
     * @return 分布式锁
     */
    // @Bean
    // @ConditionalOnMissingBean
    public DistributedLock redisDistributedLock(RedisTemplate redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }

    /**
     * 分布式锁（redisson的）
     *
     * @param redissonClient redis
     * @return 分布式锁
     */
    @Bean
    @ConditionalOnMissingBean
    public DistributedLock redisDistributedLock(RedissonClient redissonClient) {
        return new RedissonDistributedLock(redissonClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient getredisson(RedissonProperties redissonProperties) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());
        if (StrUtil.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }
}
