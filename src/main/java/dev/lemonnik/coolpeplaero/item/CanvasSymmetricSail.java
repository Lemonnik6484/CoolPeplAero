package dev.lemonnik.coolpeplaero.item;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;
import dev.simulated_team.simulated.content.blocks.symmetric_sail.SymmetricSailBlock;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = "coolpeplaero")
public class CanvasSymmetricSail {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation CANVAS_SYMMETRIC_SAIL_LOCATION = ResourceLocation.parse("coolpeplaero:canvas_symmetric_sail");

    private static class CanvasSymmetricSailBlock extends SymmetricSailBlock implements BlockSubLevelLiftProvider {
        public CanvasSymmetricSailBlock(BlockBehaviour.Properties properties) {
            super(properties, DyeColor.BROWN);
        }

        @Override
        public float sable$getParallelDragScalar() {
            return 1.55f;
        }

        @Override
        public float sable$getDirectionlessDragScalar() {
            return 0.05f;
        }

        @Override
        public @NotNull Direction sable$getNormal(BlockState state) {
            return Direction.get(Direction.AxisDirection.POSITIVE, state.getValue(SymmetricSailBlock.AXIS));
        }

        @Override
        public @NotNull ItemStack getCloneItemStack(LevelReader level, net.minecraft.core.BlockPos pos, BlockState state) {
            Item item = BuiltInRegistries.ITEM.get(CANVAS_SYMMETRIC_SAIL_LOCATION);
            if (item != null && item != Blocks.AIR.asItem()) {
                return new ItemStack(item);
            }
            return ItemStack.EMPTY;
        }

        @Override
        protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
            LOGGER.info("Registering canvas_symmetric_sail block...");
            Block block = new CanvasSymmetricSailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
            Registry.register(BuiltInRegistries.BLOCK, CANVAS_SYMMETRIC_SAIL_LOCATION, block);
            LOGGER.info("Registered canvas_symmetric_sail block");
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
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
