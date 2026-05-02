package dev.lemonnik.coolpeplaero.mixin;

import dev.simulated_team.simulated.content.blocks.steering_wheel.SteeringWheelBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SteeringWheelBlockEntity.class)
public class SteeringWheelMixin {
    @Unique
    private static final float coolPeplAero$EPSILON = 0.5F;

    @Shadow
    private float clientAngle;

    @Shadow
    private float oldClientAngle;

    @Inject(method = "tick", at = @At("TAIL"))
    private void coolPeplAero$clickSteeringWheel(CallbackInfo ci) {
        SteeringWheelBlockEntity self = (SteeringWheelBlockEntity) (Object) this;

        Level level = self.getLevel();
        if (level == null || !level.isClientSide()) return;

        boolean wasAtZero = Math.abs(oldClientAngle) < coolPeplAero$EPSILON;
        boolean isAtZero  = Math.abs(clientAngle) < coolPeplAero$EPSILON;

        if (isAtZero && !wasAtZero) {
            level.playLocalSound(self.getBlockPos(), SoundEvents.TRIPWIRE_CLICK_OFF, SoundSource.BLOCKS, 0.5F, 0.2F, false);
        }
    }
}
