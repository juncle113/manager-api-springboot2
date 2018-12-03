package com.cc.dapp.manager.api.util;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.UUID;

@Component
@CacheConfig(cacheNames = {"tokens"})
public class AuthUtil {

    /**
     * 只读权限
     */
    public static final String READ_ONLY = "read_only";

    /**
     * 可写权限
     */
    public static final String WRITE = "write";


    public static String generateToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18);
        return Base64Utils.encodeToString(token.getBytes());
    }

//    @Autowired
//    private InfoUserMapper infoUserMapper;

    @CachePut(key = "#userId")
    public String putToken(String userId, String token) {
        return token;
    }

    @CacheEvict(key = "#userId")
    public void removeToken(String userId) {
        return;
    }

//    @Cacheable(key = "#token")
//    public String getUserId(String token) {
//
//        String userId = null;
//
////            InfoUser infoUser = this.infoUserMapper.getUserByUserName(token);
//            if (infoUser != null) {
//                userId = infoUser.getUserId();
//            }
//
//        return userId;
//    }

//    public List search(String userId) {
//        CacheManager cacheManager = CacheManager.create();
//        Cache cache = cacheManager.getCache("tokens");
//        Query query = cache.createQuery();
//        query.includeKeys();
//        query.includeValues();
//        Attribute<String> attribute = cache.getSearchAttribute("value");
//        query.addCriteria(attribute.eq(userId));
//        Results results = query.execute();
//        List<Result> resultList = results.all();
//        List<String> resultKeys = new ArrayList();
//        for (Result result : resultList) {
//            resultKeys.add(String.valueOf(result.getKey()));
//        }
//        return resultKeys;
//    }
}
