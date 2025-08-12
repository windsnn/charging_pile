package com.trick.backend.common.utils;

import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> UserContext = new ThreadLocal<>();

    public static Map<String, Object> getUserContext() {
        return UserContext.get();
    }

    public static void setUserContext(Map<String, Object> map) {
        UserContext.set(map);
    }

    public static void remove() {
        UserContext.remove();
    }
}