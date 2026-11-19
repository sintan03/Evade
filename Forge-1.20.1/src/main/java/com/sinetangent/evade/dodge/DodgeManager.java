package com.sinetangent.evade.dodge;

import com.sinetangent.evade.compat.TicCompat;
import com.sinetangent.evade.config.EvadeConfig;
import com.sinetangent.evade.skill.ModSkills;
import com.sinetangent.evade.skill.Skill;
import com.sinetangent.evade.skill.SkillHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/** 回避の実行処理(サーバー側)。数値の調整は設定ファイル(EvadeConfig)で行う。 */
public class DodgeManager {

    /**
     * 回避を試みる。条件を満たさなければ何も起きない。
     *
     * @param forward 前後の入力(前が+1)
     * @param left    左右の入力(左が+1)
     */
    public static void tryDodge(ServerPlayer player, float forward, float left) {
        ItemStack stack = player.getMainHandItem();
        if (!TicCompat.canDodgeWith(stack)) return;
        if ((!player.onGround() && !TicCompat.canAirDodge(player)) || player.isPassenger() || player.isSpectator()) return;

        long now = player.level().getGameTime();
        if (now < DodgeData.getReadyAt(player)) return;

        // クライアントから届いた値は信用しない
        if (!Float.isFinite(forward) || !Float.isFinite(left)) {
            forward = 0;
            left = 0;
        }
        double length = Math.sqrt(forward * forward + left * left);
        if (length > 1.0) {
            forward /= (float) length;
            left /= (float) length;
        }

        Map<Skill, Integer> skills = SkillHelper.getActiveSkills(player);
        int window = skills.getOrDefault(ModSkills.EVADE_WINDOW, 0);
        int distance = skills.getOrDefault(ModSkills.EVADE_DISTANCE, 0);
        int recovery = skills.getOrDefault(ModSkills.DODGE_RECOVERY, 0);

        // 入力方向(体の向きを基準にした前後左右)を、ワールド座標の方向に変換する
        float yaw = player.getYRot() * Mth.DEG_TO_RAD;
        double sin = Mth.sin(yaw);
        double cos = Mth.cos(yaw);
        double speed = EvadeConfig.BASE_SPEED.get() + distance * EvadeConfig.SPEED_PER_LEVEL.get();
        double dx = (left * cos - forward * sin) * speed;
        double dz = (left * sin + forward * cos) * speed;

        player.push(dx, EvadeConfig.JUMP_SPEED.get(), dz);
        player.hurtMarked = true; // 速度をクライアントに送る

        int cooldown = Math.max(1, EvadeConfig.BASE_COOLDOWN_TICKS.get() - recovery);
        DodgeData.setEvadingUntil(player, now + EvadeConfig.BASE_INVULN_TICKS.get() + window);
        DodgeData.setReadyAt(player, now + cooldown);
        player.getCooldowns().addCooldown(stack.getItem(), cooldown); // 見た目のクールダウン表示

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDER_DRAGON_FLAP, SoundSource.PLAYERS, 1.0F, 1.3F);
    }
}
