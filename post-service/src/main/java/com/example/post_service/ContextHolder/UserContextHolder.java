package com.example.post_service.ContextHolder;

public class UserContextHolder {

    final private static ThreadLocal<Long> currentUserId=new ThreadLocal<>();

    public static Long getCurrentUserId() {
        return currentUserId.get();
    }

    static void setCurrentUserId(Long userId) {
        currentUserId.set(userId);
    }

    static void clear() {
        currentUserId.remove();
    }


}
