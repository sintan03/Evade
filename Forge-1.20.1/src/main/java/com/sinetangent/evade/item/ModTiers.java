package com.sinetangent.evade.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {

    public static final ForgeTier EVADE_1 = new ForgeTier(
            1,                                  // 採掘レベル（2 = 鉄相当）
            300,                                // 耐久値
            5.0F,                               // 採掘速度
            2.0F,                               // 攻撃力ボーナス
            10,                                 // エンチャントされやすさ
            BlockTags.NEEDS_STONE_TOOL,          // 採掘できるブロックのタグ
            () -> Ingredient.of(ModItems.EVADE_INGOT_1.get()) // 修理素材
    );

    public static final ForgeTier EVADE_2 = new ForgeTier(
            3,
            1000,
            6.0F,
            2.0F,
            15,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.EVADE_INGOT_2.get())
    );

    public static final ForgeTier EVADE_3 = new ForgeTier(
            4,
            2000,
            8.0F,
            2.0F,
            20,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.EVADE_INGOT_3.get())
    );

}