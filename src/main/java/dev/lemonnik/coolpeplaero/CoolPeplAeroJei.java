package dev.lemonnik.coolpeplaero;

import dev.simulated_team.simulated.index.SimItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.List;

@JeiPlugin
public class CoolPeplAeroJei implements IModPlugin {
    @Override
    public @NonNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(CoolPeplAero.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();

        registration.addRecipes(
            RecipeTypes.ANVIL,
            List.of(
                factory.createAnvilRecipe(
                    new ItemStack(SimItems.PLUNGER_LAUNCHER.asItem()),
                    List.of(new ItemStack(Items.COPPER_INGOT)),
                    List.of(new ItemStack(SimItems.PLUNGER_LAUNCHER.asItem())),
                    ResourceLocation.fromNamespaceAndPath(CoolPeplAero.MODID, "repair_plunger")
                )
            )
        );
    }
}