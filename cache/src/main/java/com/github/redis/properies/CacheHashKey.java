package com.github.redis.properies;

import cn.hutool.core.util.StrUtil;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.Duration;

import static cn.hutool.core.util.StrUtil.COLON;


/**
 * hash 缓存 key 封装
 *
 * @author jie
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CacheHashKey extends CacheKey {
    /**
     * redis hash field
     */
    @NonNull
    private Object field;

    public CacheHashKey(@NonNull String key, final @NonNull Object field) {
        super(key);
        this.field = field;
    }

    public CacheHashKey(@NonNull String key, final @NonNull Object field, Duration expire) {
        super(key, expire);
        this.field = field;
    }

    public CacheKey tran() {
        return new CacheKey(StrUtil.join(COLON, getKey(), getField()), getExpire());
    }
}
