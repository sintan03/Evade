package com.sinetangent.evade;

import com.sinetangent.evade.config.EvadeConfig;
import com.sinetangent.evade.fluid.ModFluids;
import com.sinetangent.evade.item.ModCreativeModeTabs;
import com.sinetangent.evade.item.ModItems;
import com.sinetangent.evade.network.ModMessages;
import com.sinetangent.evade.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Evade.MODID)
public class Evade {

    public static final String MODID = "evade";

    public Evade() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModSounds.register(modEventBus);
        ModFluids.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EvadeConfig.SPEC);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(FMLCommonSetupEvent event) {

        event.enqueueWork(ModMessages::register);

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.EVADE_SWORD_1);
            event.accept(ModItems.EVADE_HELMET_1);
            event.accept(ModItems.EVADE_CHESTPLATE_1);
            event.accept(ModItems.EVADE_LEGGINGS_1);
            event.accept(ModItems.EVADE_BOOTS_1);
            event.accept(ModItems.EVADE_SWORD_2);
            event.accept(ModItems.EVADE_HELMET_2);
            event.accept(ModItems.EVADE_CHESTPLATE_2);
            event.accept(ModItems.EVADE_LEGGINGS_2);
            event.accept(ModItems.EVADE_BOOTS_2);
            event.accept(ModItems.EVADE_SWORD_3);
            event.accept(ModItems.EVADE_HELMET_3);
            event.accept(ModItems.EVADE_CHESTPLATE_3);
            event.accept(ModItems.EVADE_LEGGINGS_3);
            event.accept(ModItems.EVADE_BOOTS_3);
            event.accept(ModItems.EVADE_HALO);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.MOLTEN_ULTIMATE_EVADE_BUCKET);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.EVADE_INGOT_1);
            event.accept(ModItems.EVADE_PLATE_1);
            event.accept(ModItems.EVADE_INGOT_2);
            event.accept(ModItems.EVADE_PLATE_2);
            event.accept(ModItems.EVADE_INGOT_3);
            event.accept(ModItems.EVADE_PLATE_3);
            event.accept(ModItems.ULTIMATE_EVADE_INGOT);
            event.accept(ModItems.EVADE_CORE);
            event.accept(ModItems.SERUM_CATALYST);
        }

    }

}