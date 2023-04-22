package moe.dituon.petpet.plugin;

import java.util.concurrent.ConcurrentHashMap;

public class Cooler {
    public static final Long DEFAULT_USER_COOLDOWN = 1000L;
    public static final Long DEFAULT_GROUP_COOLDOWN = 0L;
    public static final String DEFAULT_MESSAGE = "技能冷却中...";
    private static final ConcurrentHashMap<Long, Long> coolDownMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, Long> lockTimeMap = new ConcurrentHashMap<>();

    public static void lock(long uid, long lockTime) {
        if (lockTime <= 0L) return;
        coolDownMap.put(uid, System.currentTimeMillis());
        lockTimeMap.put(uid, lockTime);
    }

    public static boolean isLocked(long uid) {
        if (!coolDownMap.containsKey(uid) || !lockTimeMap.containsKey(uid)) return false;
        return (System.currentTimeMillis() - coolDownMap.get(uid)) <= lockTimeMap.get(uid);
    }
}