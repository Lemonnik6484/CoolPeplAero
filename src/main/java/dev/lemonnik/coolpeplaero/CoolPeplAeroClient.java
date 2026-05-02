package dev.lemonnik.coolpeplaero;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = CoolPeplAero.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CoolPeplAero.MODID, value = Dist.CLIENT)
public class CoolPeplAeroClient {
    public CoolPeplAeroClient(ModContainer container) {
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }
}
