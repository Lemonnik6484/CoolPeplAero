package dev.lemonnik.coolpeplaero.mixin;

import dev.simulated_team.simulated.content.items.plunger_launcher.PlungerLauncherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlungerLauncherItem.class)
public abstract class PlungerLauncherItemMixin extends Item {

    public PlungerLauncherItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(@NonNull ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.COPPER_INGOT) || super.isValidRepairItem(stack, repairCandidate);
    }
}