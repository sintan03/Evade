package com.sinetangent.evade.item.custom;

import com.sinetangent.evade.skill.SkillHolder;
import com.sinetangent.evade.skill.SkillSet;
import com.sinetangent.evade.skill.SkillTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * スキル付きのCurios装備。
 * どのスロットに入るかはコードではなく、data/curios/tags/items/(スロット名).json で決まる。
 */
public class SkillCurioItem extends Item implements SkillHolder {

    private final SkillSet skills;

    public SkillCurioItem(Properties properties, SkillSet skills) {
        super(properties);
        this.skills = skills;
    }

    @Override
    public SkillSet getSkillSet() {
        return skills;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        SkillTooltip.append(tooltip, skills);
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
