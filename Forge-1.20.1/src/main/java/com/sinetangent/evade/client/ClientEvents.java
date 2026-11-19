package com.sinetangent.evade.client;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.compat.TicCompat;
import com.sinetangent.evade.network.DodgePacket;
import com.sinetangent.evade.network.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * クライアント側の入力処理(フォージのイベントバス)。
 * 回避キーが押されたら、そのとき押していた移動方向(WASD)と一緒にサーバーへ「回避したい」と送る。
 * 実際に回避できるかどうかの判定(クールダウンなど)は、サーバー側が行う。
 */
@Mod.EventBusSubscriber(modid = Evade.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        // 押された回数ぶん処理する(画面を開いている間に押した分は、ここには溜まらない)
        while (ModKeys.DODGE.consumeClick()) {
            if (isDodgeStance(player)) {
                ModMessages.sendToServer(new DodgePacket(player.input.forwardImpulse, player.input.leftImpulse));
            }
        }
    }

    private static boolean isDodgeStance(Player player) {
        return TicCompat.canDodgeWith(player.getMainHandItem())
                && (player.onGround() || TicCompat.canAirDodge(player))
                && !player.isInWater()
                && !player.onClimbable()
                && !player.isPassenger()
                && !player.isSpectator()
                && !player.getAbilities().flying;
    }
}
