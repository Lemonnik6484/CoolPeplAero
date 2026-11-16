package dev.lemonnik.coolpeplaero.mixin;

import dev.lemonnik.coolpeplaero.CoolPeplAero;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Unique
    private static final ResourceLocation COOLPEPLAERO$ICON_FONT = ResourceLocation.fromNamespaceAndPath(CoolPeplAero.MODID, "icons");

    @ModifyVariable(method = "renderNameTag", at = @At("HEAD"), argsOnly = true)
    private Component coolpeplaero$addIcon(Component displayName, Entity entity) {
        if (entity instanceof Player player && CoolPeplAero.COOL_PEPL.contains(player.getUUID())) {
            Component icon = Component.literal("\uE000").withStyle(style -> style.withFont(COOLPEPLAERO$ICON_FONT));

            return Component.empty().append(icon).append(displayName);
        }

        return displayName;
    }
}