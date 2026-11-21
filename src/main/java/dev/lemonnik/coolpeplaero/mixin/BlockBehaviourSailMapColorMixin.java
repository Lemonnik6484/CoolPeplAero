package dev.lemonnik.coolpeplaero.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.lemonnik.coolpeplaero.item.ColoredSailItems;
import dev.lemonnik.coolpeplaero.item.ColoredSymmetricSailItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(value = BlockBehaviour.BlockStateBase.class, remap = false)
public abstract class BlockBehaviourSailMapColorMixin {

    @Unique
    private static final Map<String, MapColor> COLOR_TO_MAP_COLOR = Map.ofEntries(
            Map.entry("black", MapColor.COLOR_BLACK),
            Map.entry("blue", MapColor.COLOR_BLUE),
            Map.entry("brown", MapColor.COLOR_BROWN),
            Map.entry("cyan", MapColor.COLOR_CYAN),
            Map.entry("gray", MapColor.COLOR_GRAY),
            Map.entry("green", MapColor.COLOR_GREEN),
            Map.entry("light_blue", MapColor.COLOR_LIGHT_BLUE),
            Map.entry("light_gray", MapColor.COLOR_LIGHT_GRAY),
            Map.entry("lime", MapColor.COLOR_LIGHT_GREEN),
            Map.entry("magenta", MapColor.COLOR_MAGENTA),
            Map.entry("orange", MapColor.COLOR_ORANGE),
            Map.entry("pink", MapColor.COLOR_PINK),
            Map.entry("purple", MapColor.COLOR_PURPLE),
            Map.entry("red", MapColor.COLOR_RED),
            Map.entry("yellow", MapColor.COLOR_YELLOW)
    );

    @ModifyReturnValue(method = "getMapColor", at = @At("RETURN"))
    private MapColor getMapColor(MapColor original, BlockGetter reader, BlockPos pos) {
        Block block = reader.getBlockState(pos).getBlock();
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockId.getPath();

        if (path.endsWith("_symmetric_sail")) {
            String color = path.replace("_symmetric_sail", "");
            MapColor mapColor = COLOR_TO_MAP_COLOR.get(color);
            if (mapColor != null) {
                return mapColor;
            }
        }

        return original;
    }
}

