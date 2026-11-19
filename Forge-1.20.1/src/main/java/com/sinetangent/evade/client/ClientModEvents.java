package com.sinetangent.evade.client;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.compat.CuriosCompat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/** クライアント専用の登録処理(MODイベントバス)。 */
@Mod.EventBusSubscriber(modid = Evade.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onRegisterKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeys.DODGE);
    }

    /** イヴェイドヘイローを装備したときの見た目(Curiosのレンダラー)を登録する */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        if (ModList.get().isLoaded(CuriosCompat.MOD_ID)) {
            event.enqueueWork(CuriosClientCompat::register);
        }
    }

    /** TiCチェストプレートのヘイロー表示用レイヤーを、プレイヤーの描画に追加する */
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : new String[]{"default", "slim"}) {
            if (event.getSkin(skin) instanceof PlayerRenderer renderer) {
                renderer.addLayer(new HaloLayer(renderer));
            }
        }
    }
}
