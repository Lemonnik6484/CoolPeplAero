package dev.lemonnik.coolpeplaero;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Set;
import java.util.UUID;

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

    public static final Set<UUID> COOL_PEPL = Set.of(
            UUID.fromString("ed260ae6-920a-45ab-a842-973573fd81a4"), // Lemonniik
            UUID.fromString("93f972e8-d67c-4430-8dd0-919117c44206"), // JX_Snack
            UUID.fromString("6ee7c18d-574d-4e57-ab32-db6ecfed49c5"), // ipsdk
            UUID.fromString("46b8561a-d5db-4a88-a590-9fcab7832e8e"), // JerryJJ
            UUID.fromString("c0a48085-347c-4d6b-86ab-777daf9abd9c"), // Cheeteck
            UUID.fromString("48870dea-794f-4f34-ad28-c3ee2a5242e2"), // _Delek
            UUID.fromString("dd236e6a-97d8-411c-8445-f31f20332eb6"), // DrAnonymous
            UUID.fromString("0abbfdd6-fa2d-44de-88b1-830c04bafc72"), // KattenMcFlurry
            UUID.fromString("cf5db11c-1e51-4ead-a0c9-9b5ba30d4156"), // Kitsuneguara
            UUID.fromString("7abf4cef-9ec1-46b1-ad08-d7e5936fd971"), // Kszaczuch
            UUID.fromString("31f057ee-080e-48f6-aa9f-0944300da5da"), // Lucunix
            UUID.fromString("deb37816-d567-4e59-96e5-976d97b0bbb6"), // Haxalotl
            UUID.fromString("f6d2da02-dfe5-403f-95ed-2bb5975f0b21"), // Smooopz
            UUID.fromString("420f914b-de76-4910-9744-727a137fb7d3"), // ZTR_Swordsman
            UUID.fromString("3e42cbb3-01af-4d91-b5d5-6ff03dc8941f"), // Vixtram
            UUID.fromString("51921fdc-c4a2-47e0-b40d-fa74f0dc8591"), // Atlas3310
            UUID.fromString("02fbc9a0-d6d8-4536-a43d-489f14685ef6"), // Matthew_Bug
            UUID.fromString("9b162bd1-b73f-429e-a4d8-6fc2543368dc"), // WoodenYup
            UUID.fromString("3809b338-026c-4628-be34-17b70a71f1ef"), // mnkrftua
            UUID.fromString("ca731154-3761-46a3-a7b0-7bcaf583d7ea"), // Kamilskii
            UUID.fromString("1ccf49e2-012b-4a22-8b0c-6acd652f5f24"), // SlavonBubon
            UUID.fromString("2703d3d8-8e36-45e6-a68d-c450514c4c81") // Tomzitiger
    );

}
