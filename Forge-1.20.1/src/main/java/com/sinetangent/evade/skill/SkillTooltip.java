package com.sinetangent.evade.skill;

import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Map;

/** スキル付きアイテムの説明文を作る(防具とCurios装備で共通)。 */
public class SkillTooltip {

    public static void append(List<Component> tooltip, SkillSet skills) {
        boolean first = true;
        for (Map.Entry<Skill, Integer> entry : skills.asMap().entrySet()) {
            if (!first) {
                tooltip.add(Component.empty()); // スキルとスキルの間は1行空ける
            }
            tooltip.addAll(entry.getKey().getTooltip(entry.getValue()));
            first = false;
        }
    }
}
