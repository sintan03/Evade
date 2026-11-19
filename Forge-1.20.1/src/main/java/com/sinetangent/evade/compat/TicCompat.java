package com.sinetangent.evade.compat;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.item.custom.EvadeSwordItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * Tinkers' Construct の改造の判定。
 * TiC本体のクラスは使わず、ツールのNBT(tic_modifiers)を直接読むので、TiCが無くても動く。
 */
public class TicCompat {

    /** 回避の心得(武器): 回避キーで回避できる */
    public static final String DODGE_BASICS = Evade.MODID + ":dodge_basics";
    /** 回避の才能(防具): 空中でも回避できる */
    public static final String AIR_DODGE = Evade.MODID + ":air_dodge";
    /** 回避者の改造(チェストプレート): イヴェイドヘイローの全効果と見た目 */
    public static final String EVADER_HALO = Evade.MODID + ":evader_halo";

    /** TiCのツールNBTで、改造一覧が入っているキー */
    private static final String TAG_MODIFIERS = "tic_modifiers";
    private static final String TAG_NAME = "name";

    /** 回避ができる持ち物か(イヴェイドの剣、または回避の心得を付けたTiCの武器) */
    public static boolean canDodgeWith(ItemStack stack) {
        if (stack.getItem() instanceof EvadeSwordItem) return true;
        return !(stack.getItem() instanceof ArmorItem) && hasModifier(stack, DODGE_BASICS);
    }

    /** 空中でも回避できるか(回避の才能を付けた防具を、どれか1つでも着ている) */
    public static boolean canAirDodge(LivingEntity entity) {
        for (ItemStack stack : entity.getArmorSlots()) {
            if (stack.getItem() instanceof ArmorItem && hasModifier(stack, AIR_DODGE)) return true;
        }
        return false;
    }

    /** ヘイローの全効果と見た目を得られるチェストプレートか */
    public static boolean hasHaloEffect(ItemStack chest) {
        return chest.getItem() instanceof ArmorItem && hasModifier(chest, EVADER_HALO);
    }

    private static boolean hasModifier(ItemStack stack, String id) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(TAG_MODIFIERS, Tag.TAG_LIST)) return false;
        ListTag list = tag.getList(TAG_MODIFIERS, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.getCompound(i).getString(TAG_NAME))) return true;
        }
        return false;
    }
}
