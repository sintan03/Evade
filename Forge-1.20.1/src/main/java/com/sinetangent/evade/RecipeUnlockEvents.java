package com.sinetangent.evade;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/** このMODのクラフトレシピを、ワールド参加時にすべてレシピブックへ解放する。 */
@Mod.EventBusSubscriber(modid = Evade.MODID)
public class RecipeUnlockEvents {

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        List<Recipe<?>> recipes = player.server.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getId().getNamespace().equals(Evade.MODID))
                .filter(recipe -> recipe.getType() == RecipeType.CRAFTING)
                .map(recipe -> (Recipe<?>) recipe)
                .toList();

        // すでに解放済みのレシピは無視される
        player.awardRecipes(recipes);
    }
}
