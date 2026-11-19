package com.sinetangent.evade.skill.skills;

import com.sinetangent.evade.dodge.DodgeData;
import com.sinetangent.evade.config.EvadeConfig;
import com.sinetangent.evade.skill.Skill;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * 回避者。
 * <ul>
 *   <li>回避性能・回避距離の最大レベル+1(Skill#raisedMaxLevelBy で設定済み)</li>
 *   <li>回避成功後10秒間、攻撃力1.3倍</li>
 *   <li>体力が25%以下のとき、攻撃力1.3倍</li>
 *   <li>15秒に1回、自動で攻撃を回避する</li>
 *   <li>攻撃者が存在しないダメージを無効化する</li>
 * </ul>
 */
public class EvaderSkill extends Skill {

    public EvaderSkill(int color) {
        super("evader", 1, color);
    }

    @Override
    public void onAttacked(LivingEntity victim, LivingAttackEvent event, int level) {
        DamageSource source = event.getSource();

        // /kill だけは無効化しない(奈落ダメージは無効化する)
        if (source.is(DamageTypes.GENERIC_KILL)) return;

        // 攻撃者が存在しないダメージ(落下・炎・溺れ・奈落など)を無効化
        if (source.getEntity() == null) {
            event.setCanceled(true);
            return;
        }

        // 15秒に1回、自動で回避
        long now = victim.level().getGameTime();
        if (now >= DodgeData.getAutoDodgeReadyAt(victim)) {
            event.setCanceled(true);
            DodgeData.setAutoDodgeReadyAt(victim, now + EvadeConfig.AUTO_DODGE_COOLDOWN_TICKS.get());
            victim.level().playSound(null, victim.getX(), victim.getY(), victim.getZ(),
                    SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 2.0F);
        }
    }

    @Override
    public void onAttack(LivingEntity attacker, LivingEntity target, LivingHurtEvent event, int level) {
        float multiplier = 1.0F;

        // 回避成功後10秒間
        if (attacker.level().getGameTime() < DodgeData.getSuccessUntil(attacker)) {
            multiplier *= EvadeConfig.EVADER_BOOST.get().floatValue();
        }

        // 体力が25%以下
        if (attacker.getHealth() <= attacker.getMaxHealth() * EvadeConfig.EVADER_LOW_HEALTH_RATIO.get().floatValue()) {
            multiplier *= EvadeConfig.EVADER_BOOST.get().floatValue();
        }

        if (multiplier != 1.0F) {
            event.setAmount(event.getAmount() * multiplier);
        }
    }
}
