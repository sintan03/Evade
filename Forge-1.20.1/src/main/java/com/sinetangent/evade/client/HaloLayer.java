package com.sinetangent.evade.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sinetangent.evade.compat.CuriosCompat;
import com.sinetangent.evade.compat.TicCompat;
import com.sinetangent.evade.item.ModItems;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.fml.ModList;

/**
 * ヘルメット枠のヘイロー、またはTiCの改造「回避者」を付けたチェストプレートを着ているときに、ヘイローを表示するレイヤー。
 * 本物のイヴェイドヘイローを装備しているときは、二重に表示しないよう何もしない。
 */
public class HaloLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public HaloLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, AbstractClientPlayer player,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        // ヘルメット枠にヘイローを着けているか、回避者の改造を付けたチェストプレートを着ているとき
        boolean helmetHalo = player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.EVADE_HALO.get());
        if (!helmetHalo && !TicCompat.hasHaloEffect(player.getItemBySlot(EquipmentSlot.CHEST))) return;

        boolean wearingHalo = ModList.get().isLoaded(CuriosCompat.MOD_ID) && CuriosCompat.isWearingHalo(player);
        if (wearingHalo) return;

        HaloRenderer.renderHalo(poseStack, buffer, player, light, ageInTicks);
    }
}
