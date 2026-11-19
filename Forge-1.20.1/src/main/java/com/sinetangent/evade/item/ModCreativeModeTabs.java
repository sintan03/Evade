package com.sinetangent.evade.item;

import com.sinetangent.evade.Evade;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Evade.MODID);

    public static final RegistryObject<CreativeModeTab> EVADE_TAB = CREATIVE_MODE_TABS.register("evade_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.EVADE_HALO.get()))
                    .title(Component.translatable("creativetab.evade.evade_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EVADE_SWORD_1.get());
                        output.accept(ModItems.EVADE_HELMET_1.get());
                        output.accept(ModItems.EVADE_CHESTPLATE_1.get());
                        output.accept(ModItems.EVADE_LEGGINGS_1.get());
                        output.accept(ModItems.EVADE_BOOTS_1.get());
                        output.accept(ModItems.EVADE_SWORD_2.get());
                        output.accept(ModItems.EVADE_HELMET_2.get());
                        output.accept(ModItems.EVADE_CHESTPLATE_2.get());
                        output.accept(ModItems.EVADE_LEGGINGS_2.get());
                        output.accept(ModItems.EVADE_BOOTS_2.get());
                        output.accept(ModItems.EVADE_SWORD_3.get());
                        output.accept(ModItems.EVADE_HELMET_3.get());
                        output.accept(ModItems.EVADE_CHESTPLATE_3.get());
                        output.accept(ModItems.EVADE_LEGGINGS_3.get());
                        output.accept(ModItems.EVADE_BOOTS_3.get());
                        output.accept(ModItems.EVADE_HALO.get());
                        output.accept(ModItems.EVADE_INGOT_1.get());
                        output.accept(ModItems.EVADE_PLATE_1.get());
                        output.accept(ModItems.EVADE_INGOT_2.get());
                        output.accept(ModItems.EVADE_PLATE_2.get());
                        output.accept(ModItems.EVADE_INGOT_3.get());
                        output.accept(ModItems.EVADE_PLATE_3.get());
                        output.accept(ModItems.ULTIMATE_EVADE_INGOT.get());
                        output.accept(ModItems.EVADE_CORE.get());
                        output.accept(ModItems.SERUM_CATALYST.get());
                        output.accept(ModItems.MOLTEN_ULTIMATE_EVADE_BUCKET.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}