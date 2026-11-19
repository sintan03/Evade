package com.sinetangent.evade.skill;

import com.sinetangent.evade.Evade;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** 装備中のスキルを、攻撃時・被ダメージ時・定期実行のタイミングで発火させる。 */
@Mod.EventBusSubscriber(modid = Evade.MODID)
public class SkillEvents {

    /** 定期実行の間隔(tick)。20 = 1秒 */
    private static final int TICK_PERIOD = 20;

    /** ダメージ処理の前。回避のようにダメージを無効化したいスキルはここ(onAttacked)。 */
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity victim = event.getEntity();
        if (victim.level().isClientSide) return;

        SkillHelper.getActiveSkills(victim).forEach((skill, level) -> {
            if (!event.isCanceled()) {
                skill.onAttacked(victim, event, level);
            }
        });
    }

    /** ダメージ確定の直前。ダメージ量を変えたいスキルはここ(onAttack / onHurt)。 */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity victim = event.getEntity();
        if (victim.level().isClientSide) return;

        // 攻撃時(攻撃した側のスキル)
        if (event.getSource().getEntity() instanceof LivingEntity attacker && attacker != victim) {
            SkillHelper.getActiveSkills(attacker)
                    .forEach((skill, level) -> skill.onAttack(attacker, victim, event, level));
        }

        // 被ダメージ時(ダメージを受けた側のスキル)
        SkillHelper.getActiveSkills(victim)
                .forEach((skill, level) -> skill.onHurt(victim, event, level));
    }

    /** 定期実行(サーバー側のみ) */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;
        if (player.tickCount % TICK_PERIOD != 0) return;

        SkillHelper.getActiveSkills(player)
                .forEach((skill, level) -> skill.onTick(player, level));
    }
}
