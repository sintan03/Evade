package com.sinetangent.evade.dodge;

import net.minecraft.world.entity.Entity;

/**
 * 回避まわりの状態(いつまで無敵か、いつ再使用できるか、など)を保存する。
 * すべて「ゲーム時刻(tick)」で持つので、毎tickのカウントダウンは不要。
 */
public final class DodgeData {

    private static final String EVADING_UNTIL = "evade_evading_until";      // この時刻まで無敵
    private static final String SUCCESS_UNTIL = "evade_success_until";      // この時刻まで「回避成功後」の強化
    private static final String READY_AT = "evade_dodge_ready_at";          // この時刻から回避を再使用できる
    private static final String AUTO_READY_AT = "evade_auto_dodge_ready_at"; // この時刻から自動回避が使える

    private DodgeData() {
    }

    public static long getEvadingUntil(Entity e) {
        return e.getPersistentData().getLong(EVADING_UNTIL);
    }

    public static void setEvadingUntil(Entity e, long time) {
        e.getPersistentData().putLong(EVADING_UNTIL, time);
    }

    public static long getSuccessUntil(Entity e) {
        return e.getPersistentData().getLong(SUCCESS_UNTIL);
    }

    public static void setSuccessUntil(Entity e, long time) {
        e.getPersistentData().putLong(SUCCESS_UNTIL, time);
    }

    public static long getReadyAt(Entity e) {
        return e.getPersistentData().getLong(READY_AT);
    }

    public static void setReadyAt(Entity e, long time) {
        e.getPersistentData().putLong(READY_AT, time);
    }

    public static long getAutoDodgeReadyAt(Entity e) {
        return e.getPersistentData().getLong(AUTO_READY_AT);
    }

    public static void setAutoDodgeReadyAt(Entity e, long time) {
        e.getPersistentData().putLong(AUTO_READY_AT, time);
    }
}
