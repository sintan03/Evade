package com.sinetangent.evade.skill.skills;

import com.sinetangent.evade.config.EvadeConfig;
import com.sinetangent.evade.skill.Skill;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/** 攻撃(Attack Boost)。攻撃力がスキルレベルごとに0.1倍増加する(最大VII)。 */
public class AttackBoostSkill extends Skill {


    public AttackBoostSkill(int color) {
        super("attack_boost", 7, color);
    }

    @Override
    public void onAttack(LivingEntity attacker, LivingEntity target, LivingHurtEvent event, int level) {
        event.setAmount(event.getAmount() * (1.0F + EvadeConfig.ATTACK_BOOST_PER_LEVEL.get().floatValue() * level));
    }
}
