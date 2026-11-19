package com.sinetangent.evade.item;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.item.custom.EvadeCoreItem;
import com.sinetangent.evade.item.custom.EvadeSwordItem;
import com.sinetangent.evade.item.custom.SerumCatalystItem;
import com.sinetangent.evade.item.custom.SkillArmorItem;
import com.sinetangent.evade.item.custom.SkillCurioItem;
import com.sinetangent.evade.skill.ModSkills;
import com.sinetangent.evade.skill.SkillSet;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Evade.MODID);

    public static final RegistryObject<Item> EVADE_SWORD_1 = ITEMS.register("evade_sword_1",
            () -> new EvadeSwordItem(ModTiers.EVADE_1, 2, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> EVADE_SWORD_2 = ITEMS.register("evade_sword_2",
            () -> new EvadeSwordItem(ModTiers.EVADE_2, 4, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> EVADE_SWORD_3 = ITEMS.register("evade_sword_3",
            () -> new EvadeSwordItem(ModTiers.EVADE_3, 5, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> EVADE_INGOT_1 = ITEMS.register("evade_ingot_1",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVADE_INGOT_2 = ITEMS.register("evade_ingot_2",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVADE_INGOT_3 = ITEMS.register("evade_ingot_3",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ULTIMATE_EVADE_INGOT = ITEMS.register("ultimate_evade_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)) {
                @Override
                public boolean isFoil(@NotNull ItemStack stack) {
                    return true;
                }
            });

    /** 再構築コアに溶けたアルティメットイヴェイド金属を流し込んで作る。TiCの改造に使う */
    public static final RegistryObject<Item> EVADE_CORE = ITEMS.register("evade_core", EvadeCoreItem::new);

    /** イヴェイドヘイローに再構築のせせらぎを流して作る。チェストプレートの改造に使う */
    public static final RegistryObject<Item> SERUM_CATALYST = ITEMS.register("serum_catalyst", SerumCatalystItem::new);

    /** 溶融アルティメットイヴェイド金属のバケツ(ModFluids で登録される) */
    public static final java.util.function.Supplier<Item> MOLTEN_ULTIMATE_EVADE_BUCKET =
            () -> com.sinetangent.evade.fluid.ModFluids.MOLTEN_ULTIMATE_EVADE.bucket.get();

    public static final RegistryObject<Item> EVADE_PLATE_1 = ITEMS.register("evade_plate_1",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVADE_PLATE_2 = ITEMS.register("evade_plate_2",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVADE_PLATE_3 = ITEMS.register("evade_plate_3",
            () -> new Item(new Item.Properties()));

    // 防具: スキルは SkillSet で指定する(.add(スキル, レベル))
    public static final RegistryObject<Item> EVADE_HELMET_1 = ITEMS.register("evade_helmet_1",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_1, ArmorItem.Type.HELMET, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_CHESTPLATE_1 = ITEMS.register("evade_chestplate_1",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1)));

    public static final RegistryObject<Item> EVADE_LEGGINGS_1 = ITEMS.register("evade_leggings_1",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1)));

    public static final RegistryObject<Item> EVADE_BOOTS_1 = ITEMS.register("evade_boots_1",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_HELMET_2 = ITEMS.register("evade_helmet_2",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_2, ArmorItem.Type.HELMET, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1)));

    public static final RegistryObject<Item> EVADE_CHESTPLATE_2 = ITEMS.register("evade_chestplate_2",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_2, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_LEGGINGS_2 = ITEMS.register("evade_leggings_2",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_2, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_BOOTS_2 = ITEMS.register("evade_boots_2",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_2, ArmorItem.Type.BOOTS, new Item.Properties(),
                    new SkillSet().add(ModSkills.EVADE_WINDOW, 1)));

    public static final RegistryObject<Item> EVADE_HELMET_3 = ITEMS.register("evade_helmet_3",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_3, ArmorItem.Type.HELMET, new Item.Properties(),
                    new SkillSet().add(ModSkills.ATTACK_BOOST, 1).add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_CHESTPLATE_3 = ITEMS.register("evade_chestplate_3",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_3, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    new SkillSet().add(ModSkills.ATTACK_BOOST, 1).add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_LEGGINGS_3 = ITEMS.register("evade_leggings_3",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_3, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    new SkillSet().add(ModSkills.ATTACK_BOOST, 1).add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    public static final RegistryObject<Item> EVADE_BOOTS_3 = ITEMS.register("evade_boots_3",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_3, ArmorItem.Type.BOOTS, new Item.Properties(),
                    new SkillSet().add(ModSkills.ATTACK_BOOST, 1).add(ModSkills.EVADE_WINDOW, 1).add(ModSkills.EVADE_DISTANCE, 1)));

    // ヘルメット枠に装備できる。Curiosの head スロットにも入る(data/curios/tags/items/head.json)
    public static final RegistryObject<Item> EVADE_HALO = ITEMS.register("evade_halo",
            () -> new SkillArmorItem(ModArmorMaterials.EVADE_HALO, ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC),
                    new SkillSet()
                            .add(ModSkills.EVADER, 1)
                            .add(ModSkills.ATTACK_BOOST, 7)
                            .add(ModSkills.EVADE_WINDOW, 5)
                            .add(ModSkills.EVADE_DISTANCE, 5)
                            .add(ModSkills.DODGE_RECOVERY, 3)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}