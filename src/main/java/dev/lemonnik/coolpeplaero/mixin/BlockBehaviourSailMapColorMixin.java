package dev.lemonnik.coolpeplaero.mixin;

import com.simibubi.create.content.contraptions.bearing.SailBlock;
import dev.simulated_team.simulated.content.blocks.symmetric_sail.SymmetricSailBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockBehaviour.BlockStateBase.class, remap = false)
public abstract class BlockBehaviourSailMapColorMixin {

    /**
     * @author Lemonnik
     * @reason color on map
     */
    @Overwrite
    public MapColor getMapColor(BlockGetter reader, net.minecraft.core.BlockPos pos) {
        BlockState state = (BlockState) (Object) this;
        if (state.getBlock() instanceof SailBlock sailBlock) {
            DyeColor dyeColor = sailBlock.getColor();
            if (dyeColor != null) {
                return dyeColor.getMapColor();
            }
        }
        if (state.getBlock() instanceof SymmetricSailBlock symmetricSailBlock) {
            DyeColor dyeColor = symmetricSailBlock.getColor();
            if (dyeColor != null) {
                return dyeColor.getMapColor();
            }
        }
        return state.getMapColor(reader, pos);
    }
}

