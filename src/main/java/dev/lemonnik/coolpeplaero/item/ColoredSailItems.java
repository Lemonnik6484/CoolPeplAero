package dev.lemonnik.coolpeplaero.item;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = "coolpeplaero")
public class ColoredSailItems {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final List<String> COLORS = Arrays.asList(
            "black", "blue", "brown", "cyan", "gray", "green",
            "light_blue", "light_gray", "lime", "magenta", "orange",
            "pink", "purple", "red", "yellow"
    );

    private static final Set<ResourceLocation> COLORED_SAIL_LOCATIONS = Set.of(
            ResourceLocation.parse("create:black_sail"),
            ResourceLocation.parse("create:blue_sail"),
            ResourceLocation.parse("create:brown_sail"),
            ResourceLocation.parse("create:cyan_sail"),
            ResourceLocation.parse("create:gray_sail"),
            ResourceLocation.parse("create:green_sail"),
            ResourceLocation.parse("create:light_blue_sail"),
            ResourceLocation.parse("create:light_gray_sail"),
            ResourceLocation.parse("create:lime_sail"),
            ResourceLocation.parse("create:magenta_sail"),
            ResourceLocation.parse("create:orange_sail"),
            ResourceLocation.parse("create:pink_sail"),
            ResourceLocation.parse("create:purple_sail"),
            ResourceLocation.parse("create:red_sail"),
            ResourceLocation.parse("create:yellow_sail")
    );

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            LOGGER.info("Registering colored sail items...");
            for (String color : COLORS) {
                String name = color + "_sail";
                ResourceLocation blockLocation = ResourceLocation.parse("create:" + name);
                Block block = BuiltInRegistries.BLOCK.get(blockLocation);
                if (block == Blocks.AIR) {
                    LOGGER.warn("Block {} not found, skipping item registration", blockLocation);
                    continue;
                }
                ResourceLocation itemLocation = ResourceLocation.parse("create:" + name);
                BlockItem item = new BlockItem(block, new Item.Properties());
                Registry.register(BuiltInRegistries.ITEM, itemLocation, item);
                LOGGER.info("Registered item {} for block {}", itemLocation, blockLocation);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {
        Block block = event.getState().getBlock();
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        
        if (COLORED_SAIL_LOCATIONS.contains(blockId)) {
            event.getDrops().clear();
            Item item = BuiltInRegistries.ITEM.get(blockId);
            if (item != null && item != Blocks.AIR.asItem()) {
                ItemStack stack = new ItemStack(item);
                ItemEntity entity = new ItemEntity(event.getLevel(), 
                        event.getPos().getX() + 0.5, 
                        event.getPos().getY() + 0.5, 
                        event.getPos().getZ() + 0.5, 
                        stack);
                event.getDrops().add(entity);
                LOGGER.debug("Replaced drop for {} with {}", blockId, item);
            }
        }
    }
}
