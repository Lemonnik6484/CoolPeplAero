package dev.lemonnik.coolpeplaero.item;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import com.simibubi.create.content.contraptions.bearing.SailBlock;
import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = "coolpeplaero")
public class CanvasSail {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String FD_MODID = "farmersdelight";
    private static final ResourceLocation CANVAS_SAIL_LOCATION = ResourceLocation.parse("coolpeplaero:canvas_sail");

    private static class CanvasSailBlock extends SailBlock implements BlockSubLevelLiftProvider {
        public CanvasSailBlock(BlockBehaviour.Properties properties) {
            super(properties, false, DyeColor.WHITE);
        }

        @Override
        public float sable$getParallelDragScalar() {
            return 0.55f;
        }

        @Override
        public float sable$getDirectionlessDragScalar() {
            return 0.05f;
        }

        @Override
        public @NotNull Direction sable$getNormal(BlockState state) {
            return state.getValue(BlockStateProperties.FACING).getOpposite();
        }
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegisterEvent event) {
        if (!ModList.get().isLoaded(FD_MODID)) {
            return;
        }
        if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
            LOGGER.info("Registering canvas_sail block...");
            Block block = new CanvasSailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
            Registry.register(BuiltInRegistries.BLOCK, CANVAS_SAIL_LOCATION, block);
            LOGGER.info("Registered canvas_sail block");
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        if (!ModList.get().isLoaded(FD_MODID)) {
            return;
        }
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            LOGGER.info("Registering canvas_sail item...");
            Block block = BuiltInRegistries.BLOCK.get(CANVAS_SAIL_LOCATION);
            if (block == Blocks.AIR) {
                LOGGER.warn("canvas_sail block not found, skipping item registration");
                return;
            }
            BlockItem item = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, CANVAS_SAIL_LOCATION, item);
            LOGGER.info("Registered canvas_sail item");
        }
    }
}
