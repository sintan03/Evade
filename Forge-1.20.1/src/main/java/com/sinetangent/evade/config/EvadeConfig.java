package com.sinetangent.evade.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * 設定ファイル(config/evade-common.toml)。
 * ゲーム内の数値はここで調整できる。サーバー側の値が使われる。
 */
public class EvadeConfig {

    public static final ForgeConfigSpec SPEC;

    // --- 回避 ---
    public static final ForgeConfigSpec.IntValue BASE_INVULN_TICKS;
    public static final ForgeConfigSpec.IntValue BASE_COOLDOWN_TICKS;
    public static final ForgeConfigSpec.DoubleValue BASE_SPEED;
    public static final ForgeConfigSpec.DoubleValue SPEED_PER_LEVEL;
    public static final ForgeConfigSpec.DoubleValue JUMP_SPEED;
    public static final ForgeConfigSpec.IntValue SUCCESS_BUFF_TICKS;
    public static final ForgeConfigSpec.IntValue AUTO_DODGE_COOLDOWN_TICKS;

    // --- スキル ---
    public static final ForgeConfigSpec.DoubleValue EVADER_BOOST;
    public static final ForgeConfigSpec.DoubleValue EVADER_LOW_HEALTH_RATIO;
    public static final ForgeConfigSpec.DoubleValue ATTACK_BOOST_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        b.comment("回避の設定").push("dodge");
        BASE_INVULN_TICKS = b.comment("基本の無敵時間(tick)。回避性能1レベルごとに+1")
                .defineInRange("baseInvulnTicks", 3, 0, 200);
        BASE_COOLDOWN_TICKS = b.comment("基本のクールダウン(tick)。回避再使用1レベルごとに-1")
                .defineInRange("baseCooldownTicks", 20, 1, 1200);
        BASE_SPEED = b.comment("基本の水平速度")
                .defineInRange("baseSpeed", 0.3, 0.0, 5.0);
        SPEED_PER_LEVEL = b.comment("回避距離1レベルごとの速度の増加量")
                .defineInRange("speedPerLevel", 0.1, 0.0, 5.0);
        JUMP_SPEED = b.comment("回避時の上向きの速度(通常のジャンプは約0.42)")
                .defineInRange("jumpSpeed", 0.5, 0.0, 5.0);
        SUCCESS_BUFF_TICKS = b.comment("回避成功後の強化が続く時間(tick)。200 = 10秒")
                .defineInRange("successBuffTicks", 200, 0, 72000);
        AUTO_DODGE_COOLDOWN_TICKS = b.comment("回避者の自動回避の再使用時間(tick)。300 = 15秒")
                .defineInRange("autoDodgeCooldownTicks", 300, 0, 72000);
        b.pop();

        b.comment("スキルの設定").push("skills");
        EVADER_BOOST = b.comment("回避者: 攻撃力の倍率(回避成功後、体力が少ないとき)")
                .defineInRange("evaderBoost", 1.3, 1.0, 100.0);
        EVADER_LOW_HEALTH_RATIO = b.comment("回避者: 体力がこの割合以下で攻撃力が上がる(0.25 = 25%)")
                .defineInRange("evaderLowHealthRatio", 0.25, 0.0, 1.0);
        ATTACK_BOOST_PER_LEVEL = b.comment("攻撃: スキルレベル1ごとの攻撃力の増加(0.1 = +0.1倍)")
                .defineInRange("attackBoostPerLevel", 0.1, 0.0, 100.0);
        b.pop();

        SPEC = b.build();
    }
}
