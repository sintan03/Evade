package com.sinetangent.evade.fluid;

import com.sinetangent.evade.Evade;
import com.sinetangent.evade.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 溶けたアルティメットイヴェイド金属の液体(Tinkers' Construct の精錬炉・鋳造台で使う)。
 * 液体ブロックとバケツも登録してあるので、ワールドに置くこともできる。
 */
public class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Evade.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Evade.MODID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Evade.MODID);

    public static final Molten MOLTEN_ULTIMATE_EVADE = new Molten("molten_ultimate_evade");

    /** 液体の種類・源・流れ・ブロック・バケツのセット */
    public static class Molten {
        public final RegistryObject<FluidType> type;
        public final RegistryObject<FlowingFluid> source;
        public final RegistryObject<FlowingFluid> flowing;
        public final RegistryObject<LiquidBlock> block;
        public final RegistryObject<Item> bucket;
        private ForgeFlowingFluid.Properties properties;

        private Molten(String name) {
            ResourceLocation still = new ResourceLocation(Evade.MODID, "block/" + name + "_still");
            ResourceLocation flow = new ResourceLocation(Evade.MODID, "block/" + name + "_flowing");

            this.type = FLUID_TYPES.register(name, () -> new MoltenFluidType(still, flow));
            this.source = FLUIDS.register(name, () -> new ForgeFlowingFluid.Source(this.properties));
            this.flowing = FLUIDS.register("flowing_" + name, () -> new ForgeFlowingFluid.Flowing(this.properties));
            this.block = BLOCKS.register(name, () -> new LiquidBlock(this.source,
                    BlockBehaviour.Properties.copy(Blocks.LAVA).noLootTable()));
            this.bucket = ModItems.ITEMS.register(name + "_bucket", () -> new BucketItem(this.source,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
            this.properties = new ForgeFlowingFluid.Properties(this.type, this.source, this.flowing)
                    .block(this.block).bucket(this.bucket);
        }
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
