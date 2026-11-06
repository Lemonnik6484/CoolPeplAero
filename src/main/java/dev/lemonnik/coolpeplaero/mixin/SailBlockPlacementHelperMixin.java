package dev.lemonnik.coolpeplaero.mixin;

import com.simibubi.create.content.contraptions.bearing.SailBlock;
import net.createmod.catnip.placement.IPlacementHelper;
import net.createmod.catnip.placement.PlacementHelpers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SailBlock.class, remap = false)
public abstract class SailBlockPlacementHelperMixin {

	@Shadow @Final private static int placementHelperId;

	@Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
	private void coolpeplaero$handleColoredSailPlacement(
		ItemStack stack,
		BlockState state,
		Level level,
		net.minecraft.core.BlockPos pos,
		Player player,
		InteractionHand hand,
		BlockHitResult hitResult,
		CallbackInfoReturnable<ItemInteractionResult> ci
	) {
		if (!player.isShiftKeyDown() && player.mayBuild() && stack.getItem() instanceof BlockItem blockItem
			&& blockItem.getBlock() instanceof SailBlock) {
			IPlacementHelper placementHelper = PlacementHelpers.get(placementHelperId);
            placementHelper.getOffset(player, level, state, pos, hitResult)
                    .placeInWorld(level, blockItem, player, hand, hitResult);
            ci.setReturnValue(ItemInteractionResult.SUCCESS);
        }
	}
}
