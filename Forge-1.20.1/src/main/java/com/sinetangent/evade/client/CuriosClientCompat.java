package com.sinetangent.evade.client;

import com.sinetangent.evade.item.ModItems;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

/** Curios導入時だけ呼ぶ、ヘイローの見た目の登録。 */
public class CuriosClientCompat {

    public static void register() {
        CuriosRendererRegistry.register(ModItems.EVADE_HALO.get(), HaloCurioRenderer::new);
    }
}
