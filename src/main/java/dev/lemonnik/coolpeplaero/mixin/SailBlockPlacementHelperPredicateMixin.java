package dev.lemonnik.coolpeplaero.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.bearing.SailBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;

@Mixin(targets = {"com.simibubi.create.content.contraptions.bearing.SailBlock$PlacementHelper"}, remap = false)
abstract class SailBlockPlacementHelperPredicateMixin {

	/**
	 * @author Lemonnik
	 * @reason To do colored sail placement suggestions
	 */
	@Overwrite
	public Predicate<ItemStack> getItemPredicate() {
		return i -> AllBlocks.SAIL.isIn(i)
			|| AllBlocks.SAIL_FRAME.isIn(i)
			|| (i.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SailBlock);
	}
}
