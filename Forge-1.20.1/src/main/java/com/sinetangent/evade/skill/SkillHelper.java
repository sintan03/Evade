package com.sinetangent.evade.skill;

import com.sinetangent.evade.compat.CuriosCompat;
import com.sinetangent.evade.compat.TicCompat;
import com.sinetangent.evade.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

import java.util.HashMap;
import java.util.Map;

public class SkillHelper {

    /**
     * 装備中のスキルを合計して返す(防具4部位 + Curios装備)。
     * 同じスキルは足し算され、最後に最大レベルで頭打ちになる。
     */
    public static Map<Skill, Integer> getActiveSkills(LivingEntity entity) {
        Map<Skill, Integer> raw = new HashMap<>();

        for (ItemStack stack : entity.getArmorSlots()) {
            addFrom(raw, stack);
        }

        // TiCの改造「イヴェイドコア」を付けたチェストプレートは、イヴェイドヘイローと同じ効果を持つ
        if (TicCompat.hasHaloEffect(entity.getItemBySlot(EquipmentSlot.CHEST))) {
            addFrom(raw, new ItemStack(ModItems.EVADE_HALO.get()));
        }

        if (ModList.get().isLoaded(CuriosCompat.MOD_ID)) {
            CuriosCompat.addSkills(entity, raw);
        }

        Map<Skill, Integer> capped = new HashMap<>();
        raw.forEach((skill, level) -> capped.put(skill, Math.min(level, skill.getEffectiveMaxLevel(raw))));
        return capped;
    }

    public static int getLevel(LivingEntity entity, Skill skill) {
        return getActiveSkills(entity).getOrDefault(skill, 0);
    }

    public static void addFrom(Map<Skill, Integer> total, ItemStack stack) {
        if (stack.getItem() instanceof SkillHolder holder) {
            holder.getSkillSet().asMap().forEach((skill, level) -> total.merge(skill, level, Integer::sum));
        }
    }
}
