package com.sinetangent.evade.dodge;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.config.EvadeConfig;
import com.sinetangent.evade.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** 回避中の無敵判定。 */
@Mod.EventBusSubscriber(modid = Evade.MODID)
public class DodgeEvents {

    /**
     * スキル(自動回避など)より先に判定したいので、優先度を HIGH にしている。
     * 回避中は「攻撃者がいるダメージ」だけを無効化する(落下や炎は防げない)。
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onAttacked(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        DamageSource source = event.getSource();
        if (source.getEntity() == null) return;
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;

        long now = player.level().getGameTime();
        if (now >= DodgeData.getEvadingUntil(player)) return;

        // 回避成功(無敵時間中に、無効化できるダメージを受けた)
        event.setCanceled(true);
        DodgeData.setSuccessUntil(player, now + EvadeConfig.SUCCESS_BUFF_TICKS.get());

        // 回避成功の炸裂音(自動回避では鳴らない)
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                ModSounds.DODGE_BURST.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
