package com.sinetangent.evade.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

/** 溶けた金属の液体の種類(見た目と性質)。 */
public class MoltenFluidType extends FluidType {

    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;

    public MoltenFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
        super(FluidType.Properties.create()
                .density(2000)
                .viscosity(6000)
                .temperature(1000)
                .lightLevel(15));
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }
        });
    }
}
