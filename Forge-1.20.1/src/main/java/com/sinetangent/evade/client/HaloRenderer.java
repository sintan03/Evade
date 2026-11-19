package com.sinetangent.evade.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.sinetangent.evade.Evade;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

/**
 * イヴェイドヘイローの見た目。
 * プレイヤーの背後で、5枚の円盤(魔法陣)がそれぞれ違う速さで回る。
 * 元アドオンの evade_halo.geo.json / evade_halo.animation.json と同じ配置・回転速度。
 */
public class HaloRenderer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Evade.MODID, "textures/entity/halo.png");
    private static final float TEXTURE_SIZE = 128F;
    private static final float PIXEL = 1F / 16F;

    /** 円盤の中心(モデル座標、ピクセル)。肩の4px上、背中の16px後ろ */
    private static final float CENTER_Y = -4F;
    private static final float CENTER_Z = 18F;

    /**
     * 円盤1枚ぶんの設定。
     *
     * @param half  半径(px)。1辺は半径の2倍
     * @param u     テクスチャ上の左上のX(px)
     * @param v     テクスチャ上の左上のY(px)
     * @param size  テクスチャ上の1辺(px)
     * @param flip  左右反転して貼るか
     * @param speed 回転速度(度/秒)。符号が回転の向き
     */
    private record Disc(float half, float u, float v, float size, boolean flip, float speed) {
    }

    private static final Disc[] DISCS = {
            new Disc(16, 0, 0, 32, true, -30),
            new Disc(32, 64, 0, 64, true, 20),
            new Disc(32, 0, 64, 64, false, -10),
            new Disc(16, 32, 0, 32, true, 50),
            new Disc(16, 0, 32, 32, true, -70),
    };

    /** ヘイローを描く。Curiosのレンダラーと、ヘルメット・チェストプレート用のレイヤーの両方から呼ばれる */
    public static void renderHalo(PoseStack poseStack, MultiBufferSource buffer, LivingEntity entity,
                                  int light, float ageInTicks) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        float seconds = ageInTicks / 20F;

        poseStack.pushPose();

        // しゃがんだときは体と一緒に傾ける
        if (entity.isCrouching()) {
            poseStack.translate(0.0F, 0.1875F, 0.0F);
            poseStack.mulPose(Axis.XP.rotation(0.5F));
        }

        for (Disc disc : DISCS) {
            poseStack.pushPose();
            poseStack.translate(0.0F, CENTER_Y * PIXEL, CENTER_Z * PIXEL);
            poseStack.mulPose(Axis.ZP.rotationDegrees(disc.speed() * seconds));
            drawDisc(poseStack, consumer, disc, light);
            poseStack.popPose();
        }

        poseStack.popPose();
    }

    private static void drawDisc(PoseStack poseStack, VertexConsumer consumer, Disc disc, int light) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normal = pose.normal();

        float h = disc.half() * PIXEL;
        float u1 = disc.u() / TEXTURE_SIZE;
        float u2 = (disc.u() + disc.size()) / TEXTURE_SIZE;
        float v1 = disc.v() / TEXTURE_SIZE;
        float v2 = (disc.v() + disc.size()) / TEXTURE_SIZE;
        if (disc.flip()) {
            float tmp = u1;
            u1 = u2;
            u2 = tmp;
        }

        // y軸は下向きがプラス。(x, y) = 右上 → 左上 → 左下 → 右下 の順
        vertex(consumer, matrix, normal, h, -h, u2, v1, light);
        vertex(consumer, matrix, normal, -h, -h, u1, v1, light);
        vertex(consumer, matrix, normal, -h, h, u1, v2, light);
        vertex(consumer, matrix, normal, h, h, u2, v2, light);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix, Matrix3f normal,
                               float x, float y, float u, float v, int light) {
        consumer.vertex(matrix, x, y, 0.0F)
                .color(255, 255, 255, 255)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 0.0F, -1.0F)
                .endVertex();
    }
}
