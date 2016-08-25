package com.zz.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/8/24.
 */
public class CacheMap {

    private Map<String,Object> hashMap = new ConcurrentHashMap();
}
