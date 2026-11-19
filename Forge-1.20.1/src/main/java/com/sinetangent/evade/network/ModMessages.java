package com.sinetangent.evade.network;

import com.sinetangent.evade.Evade;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/** クライアント → サーバーへの通信(回避の入力を送る)。 */
public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";

    private static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Evade.MODID, "main"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    /** FMLCommonSetupEvent の enqueueWork 内で呼ぶ */
    public static void register() {
        int id = 0;

        CHANNEL.messageBuilder(DodgePacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(DodgePacket::new)
                .encoder(DodgePacket::encode)
                .consumerMainThread(DodgePacket::handle)
                .add();
    }

    public static void sendToServer(Object message) {
        CHANNEL.sendToServer(message);
    }
}
