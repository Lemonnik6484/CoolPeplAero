package dev.lemonnik.coolpeplaero;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

@EventBusSubscriber(modid = "coolpeplaero")
public class CoolPeplAeroTab {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String TAB_NAME = "coolpeplaero_tab";
    public static CreativeModeTab TAB = null;

    private static final List<String> COLORS = Arrays.asList(
            "black", "blue", "brown", "cyan", "gray", "green",
            "light_blue", "light_gray", "lime", "magenta", "orange",
            "pink", "purple", "red", "yellow"
    );

    @SubscribeEvent
    public static void onRegisterCreativeModeTab(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
            LOGGER.info("Registering Cool Pepl Aero creative tab...");
            TAB = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.coolpeplaero_tab"))
                    .icon(() -> new ItemStack(getItemOrBlock("coolpeplaero:canvas_sail")))
                    .displayItems((parameters, output) -> {
                        output.accept(getItemOrBlock("coolpeplaero:canvas_sail"));
                        output.accept(getItemOrBlock("coolpeplaero:canvas_symmetric_sail"));
                        output.accept(getItemOrBlock("coolpeplaero:canvas_envelope"));

                        for (String color : COLORS) {
                            String sailName = color + "_sail";
                            Item sailItem = getItemOrBlock("create:" + sailName);
                            if (sailItem != null) {
                                output.accept(sailItem);
                            }
                        }
                    })
                    .build();

            net.minecraft.core.Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                    ResourceLocation.parse("coolpeplaero:" + TAB_NAME), TAB);
            LOGGER.info("Registered Cool Pepl Aero creative tab");
        }
    }

    private static Item getItemOrBlock(String id) {
        ResourceLocation location = ResourceLocation.parse(id);
        Item item = BuiltInRegistries.ITEM.get(location);
        if (item != null && item != Blocks.AIR.asItem()) {
            return item;
        }
        Block block = BuiltInRegistries.BLOCK.get(location);
        if (block != null && block != Blocks.AIR) {
            return block.asItem();
        }
        return null;
    }
}
