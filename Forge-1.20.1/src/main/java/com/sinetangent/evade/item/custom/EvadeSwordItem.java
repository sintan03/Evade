package com.sinetangent.evade.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EvadeSwordItem extends SwordItem {

    public EvadeSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, @NotNull TooltipFlag flag) {
        // %s の部分に、現在の回避キー(デフォルトはR)が表示される。キー設定を変えれば説明文も変わる
        tooltip.add(Component.translatable("tooltip.evade.evade_sword_1.line1",
                        Component.keybind("key.evade.dodge").withStyle(ChatFormatting.YELLOW))
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.evade.evade_sword_1.line2")
                .withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }

}