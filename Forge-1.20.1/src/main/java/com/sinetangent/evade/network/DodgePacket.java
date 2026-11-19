package com.sinetangent.evade.network;

import com.sinetangent.evade.dodge.DodgeManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/** 「回避したい」という入力(どの方向のキーを押していたか)をサーバーに伝える。 */
public class DodgePacket {

    private final float forward;
    private final float left;

    public DodgePacket(float forward, float left) {
        this.forward = forward;
        this.left = left;
    }

    public DodgePacket(FriendlyByteBuf buf) {
        this.forward = buf.readFloat();
        this.left = buf.readFloat();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(forward);
        buf.writeFloat(left);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                DodgeManager.tryDodge(player, forward, left);
            }
        });
        return true;
    }
}
