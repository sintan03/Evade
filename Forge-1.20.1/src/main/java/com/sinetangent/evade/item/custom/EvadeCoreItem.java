package com.sinetangent.evade.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/** 再構築コア(溶融アルティメットイヴェイド金属を流し込んだもの)。名前は青、説明文はレア色。 */
public class EvadeCoreItem extends Item {

    public EvadeCoreItem() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.BLUE);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.evade.evade_core").withStyle(Rarity.RARE.color));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
