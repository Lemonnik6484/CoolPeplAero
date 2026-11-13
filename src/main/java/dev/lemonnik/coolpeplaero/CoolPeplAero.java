package dev.lemonnik.coolpeplaero;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CoolPeplAero.MODID)
public class CoolPeplAero {
    public static final String MODID = "coolpeplaero";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CoolPeplAero(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onRegisterItems);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Cooling the Pepls!");
    }

    public void onRegisterItems(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            Registry.register(BuiltInRegistries.ITEM, MODID+":icon", new Item(new Item.Properties()));
        }
    }
}
