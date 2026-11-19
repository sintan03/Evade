package com.sinetangent.evade.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

/** キー割り当て(クライアント専用)。デフォルトはRキーで、ゲーム内の「操作」設定から変更できる。 */
public class ModKeys {

    public static final KeyMapping DODGE = new KeyMapping(
            "key.evade.dodge",                 // 言語ファイルのキー(操作設定に表示される名前)
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,                   // デフォルトのキー
            "key.categories.evade");           // 操作設定でのカテゴリ名
}
