package dev.lemonnik.coolpeplaero.item;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;
import dev.simulated_team.simulated.content.blocks.symmetric_sail.SymmetricSailBlock;

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
public class CanvasSymmetricSail {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String FD_MODID = "farmersdelight";
    private static final ResourceLocation CANVAS_SYMMETRIC_SAIL_LOCATION = ResourceLocation.parse("coolpeplaero:canvas_symmetric_sail");

    private static class CanvasSymmetricSailBlock extends SymmetricSailBlock implements BlockSubLevelLiftProvider {
        public CanvasSymmetricSailBlock(BlockBehaviour.Properties properties, DyeColor color) {
            super(properties, color);
        }

        @Override
        public float sable$getParallelDragScalar() {
            return 0.875f;
        }

        @Override
        public float sable$getDirectionlessDragScalar() {
            return 0.034f;
        }

        @Override
        public float sable$getLiftScalar() {
            return 0f;
        }

        @Override
        public @NotNull Direction sable$getNormal(BlockState state) {
            return Direction.get(Direction.AxisDirection.POSITIVE, state.getValue(SymmetricSailBlock.AXIS));
        }
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegisterEvent event) {
        if (!ModList.get().isLoaded(FD_MODID)) {
            return;
        }
        if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
            LOGGER.info("Registering canvas_symmetric_sail block...");
            Block block = new CanvasSymmetricSailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL), DyeColor.WHITE);
            Registry.register(BuiltInRegistries.BLOCK, CANVAS_SYMMETRIC_SAIL_LOCATION, block);
            LOGGER.info("Registered canvas_symmetric_sail block");
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        if (!ModList.get().isLoaded(FD_MODID)) {
            return;
        }
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            LOGGER.info("Registering canvas_symmetric_sail item...");
            Block block = BuiltInRegistries.BLOCK.get(CANVAS_SYMMETRIC_SAIL_LOCATION);
            if (block == Blocks.AIR) {
                LOGGER.warn("canvas_symmetric_sail block not found, skipping item registration");
                return;
            }
            BlockItem item = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, CANVAS_SYMMETRIC_SAIL_LOCATION, item);
            LOGGER.info("Registered canvas_symmetric_sail item");
        }
    }
}
