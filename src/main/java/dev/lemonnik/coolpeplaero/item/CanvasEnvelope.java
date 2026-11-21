package dev.lemonnik.coolpeplaero.item;

import com.mojang.logging.LogUtils;
import com.simibubi.create.api.schematic.requirement.SpecialBlockItemRequirement;
import dev.eriksonn.aeronautics.content.blocks.hot_air.envelope.Envelope;
import dev.eriksonn.aeronautics.content.blocks.hot_air.envelope.EnvelopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
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
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = "coolpeplaero")
public class CanvasEnvelope {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation CANVAS_ENVELOPE_LOCATION = ResourceLocation.parse("coolpeplaero:canvas_envelope");

    private static class CanvasEnvelopeBlock extends EnvelopeBlock implements Envelope, SpecialBlockItemRequirement {
        public CanvasEnvelopeBlock(Properties properties) {
            super(properties, DyeColor.BROWN);
        }

        @Override
        protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        @Override
        public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
            Item item = BuiltInRegistries.ITEM.get(CANVAS_ENVELOPE_LOCATION);
            if (item != null && item != Blocks.AIR.asItem()) {
                return new ItemStack(item);
            }
            return ItemStack.EMPTY;
        }
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
            LOGGER.info("Registering canvas_envelope block...");
            Block block = new CanvasEnvelopeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
            Registry.register(BuiltInRegistries.BLOCK, CANVAS_ENVELOPE_LOCATION, block);
            LOGGER.info("Registered canvas_envelope block");
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            LOGGER.info("Registering canvas_envelope item...");
            Block block = BuiltInRegistries.BLOCK.get(CANVAS_ENVELOPE_LOCATION);
            if (block == Blocks.AIR) {
                LOGGER.warn("canvas_envelope block not found, skipping item registration");
                return;
            }
            BlockItem item = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, CANVAS_ENVELOPE_LOCATION, item);
            LOGGER.info("Registered canvas_envelope item");
        }
    }
}
