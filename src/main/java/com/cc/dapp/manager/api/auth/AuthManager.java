package com.cc.dapp.manager.api.auth;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.UUID;

/**
 * 鉴权Manager
 *
 * @author sunli
 * @date 2018/12/07
 */
@Component
@CacheConfig(cacheNames = {AuthManager.TOKENS})
public class AuthManager {

    /**
     * 检查权限：只读权限
     */
    public static final int READ = 1;

    /**
     * 检查权限：可写权限
     */
    public static final int WRITE = 2;

    /**
     * token缓存名
     */
    public static final String TOKENS = "tokens";

    /**
     * 当前登录账号id
     */
    public static final String CURRENT_ID = "currentId";

    /**
     * 生成token
     *
     * @return token
     */
    public static String generateToken(String id) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        return Base64Utils.encodeToString(uuid.concat("_").concat(id).getBytes());
    }

    /**
     * 根据token取得id
     *
     * @param token token
     * @return id
     */
    public static String getIdByToken(String token) {
        return new String(Base64Utils.decodeFromString(token)).split("_")[1];
    }

    /**
     * 缓存token
     *
     * @return token
     */
    @CachePut(key = "#id")
    public String createToken(String id) {
        return generateToken(id);
    }

    /**
     * 清除缓存的token
     */
    @CacheEvict(key = "#id")
    public void removeToken(String id) {
        return;
    }

    /**
     * 取得缓存的token
     *
     * @return token
     */
    @Cacheable(key = "#id")
    public String getToken(String id) {
        return null;
    }
}
