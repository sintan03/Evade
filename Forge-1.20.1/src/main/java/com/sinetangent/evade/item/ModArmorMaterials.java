package com.sinetangent.evade.item;

import com.sinetangent.evade.Evade;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    EVADE_1("evade_1",
            12,                                  // 耐久倍率（鉄15、ダイヤ33、ネザライト37）
            new int[]{2, 5, 4, 1},               // 防御力: ヘルメット, チェストプレート, レギンス, ブーツ
            10,                                  // エンチャントされやすさ
            SoundEvents.ARMOR_EQUIP_IRON,        // 装備時の音
            1.0F,                                // 防具強度（toughness）
            0.0F,                                // ノックバック耐性
            () -> Ingredient.of(ModItems.EVADE_PLATE_1.get())), // 修理素材

    EVADE_2("evade_2",
            24,
            new int[]{3, 6, 5, 2},
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0F,
            0.0F,
            () -> Ingredient.of(ModItems.EVADE_PLATE_2.get())),

    EVADE_3("evade_3",
            35,
            new int[]{4, 7, 6, 3},
            20,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0F,
            0.0F,
            () -> Ingredient.of(ModItems.EVADE_PLATE_3.get())),

    /** イヴェイドヘイロー(ヘルメット枠。防御力20) */
    EVADE_HALO("evade_halo",
            37,
            new int[]{20, 0, 0, 0},
            25,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F,
            0.0F,
            () -> Ingredient.of(ModItems.ULTIMATE_EVADE_INGOT.get()));


    // ヘルメット, チェストプレート, レギンス, ブーツ の順（バニラと同じ基礎値）
    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    private final String name;
    private final int durabilityMultiplier;
    private final int[] defense;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, int[] defense, int enchantability,
                      SoundEvent equipSound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defense = defense;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = Lazy.of(repairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return defense[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return Evade.MODID + ":" + name;   // この名前がテクスチャのファイル名に使われる
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}