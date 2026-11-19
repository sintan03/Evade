package com.sinetangent.evade.compat;

import com.sinetangent.evade.item.ModItems;
import com.sinetangent.evade.skill.Skill;
import com.sinetangent.evade.skill.SkillHelper;
import com.sinetangent.evade.skill.SkillHolder;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Map;

/**
 * Curios APIを使う処理をまとめたクラス。
 * Curiosが導入されているときだけ呼ぶこと(ModList.get().isLoaded("curios") で確認する)。
 * このクラスを呼ばない限り、Curiosのクラスは読み込まれない。
 */
public class CuriosCompat {

    public static final String MOD_ID = "curios";

    /** Curiosスロットに装備しているスキル付きアイテムのスキルを足す */
    public static void addSkills(LivingEntity entity, Map<Skill, Integer> raw) {
        CuriosApi.getCuriosInventory(entity).ifPresent(handler ->
                handler.findCurios(stack -> stack.getItem() instanceof SkillHolder)
                        .forEach(result -> SkillHelper.addFrom(raw, result.stack())));
    }

    /** イヴェイドヘイローをCuriosスロットに装備しているか */
    public static boolean isWearingHalo(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .map(handler -> handler.findFirstCurio(ModItems.EVADE_HALO.get()).isPresent())
                .orElse(false);
    }
}
