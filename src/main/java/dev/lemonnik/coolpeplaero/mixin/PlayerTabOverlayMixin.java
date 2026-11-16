package dev.lemonnik.coolpeplaero.mixin;

import dev.lemonnik.coolpeplaero.CoolPeplAero;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerTabOverlay.class)
public class PlayerTabOverlayMixin {

    @Unique
    private static final ResourceLocation COOLPEPLAERO$ICON_FONT = ResourceLocation.fromNamespaceAndPath(CoolPeplAero.MODID, "icons");

    @Inject(method = "getNameForDisplay", at = @At("RETURN"), cancellable = true)
    private void coolpeplaero$addIcon(PlayerInfo info, CallbackInfoReturnable<Component> cir) {
        if (CoolPeplAero.COOL_PEPL.contains(info.getProfile().getId())) {
            Component icon = Component.literal("\uE000").withStyle(style -> style.withFont(COOLPEPLAERO$ICON_FONT));

            cir.setReturnValue(Component.empty().append(icon).append(cir.getReturnValue()));
        }
    }
}