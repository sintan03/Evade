package com.sinetangent.evade.sound;

import com.sinetangent.evade.Evade;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 独自の効果音。実際に鳴る音は assets/evade/sounds.json で決まる。
 */
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Evade.MODID);

    /** 回避したときの炸裂音(ウィンドチャージの炸裂音の代わり) */
    public static final RegistryObject<SoundEvent> DODGE_BURST = registerSound("dodge_burst");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Evade.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
