package dev.lemonnik.coolpeplaero;

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
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Cooling the Pepls!");
    }
}
