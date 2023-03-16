package com.bobvarioa.kubejsarsnoveau.recipes;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantmentRecipe;
import com.hollingsworth.arsnouveau.setup.RecipeRegistry;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRecipeJS extends RecipeJS {
    private static final RecipeSerializer<EnchantmentRecipe> serializer = RecipeRegistry.ENCHANTMENT_SERIALIZER.get();;
    private EnchantmentRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new EnchantmentRecipe(
            parseItemInputList(args.get(0)),
            ForgeRegistries.ENCHANTMENTS.getValue(getAsID(args.get(1))),
            args.getInt(2, 0),
            args.getInt(3, 0)
        );
    }

    protected ResourceLocation getAsID(Object o) {
        if (o instanceof ResourceLocation rl)
            return rl;
        return new ResourceLocation(o.toString());
    }


    @Override
    public void deserialize() {
        recipe = serializer.fromJson(new ResourceLocation("kubejs:empty"), json);
    }

    @Override
    public void serialize() {
        for (var entry : recipe.asRecipe().getAsJsonObject().entrySet()) {
            json.add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        var ings = recipe.pedestalItems;
        for (Ingredient ing : ings) {
            if (match.contains(ing)) {
                return true;
            }
        }
        return match.contains(recipe.reagent);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        var ings = recipe.pedestalItems;
        var changed = false;
        for (int i = 0; i < ings.size(); i++) {
            var ing = ings.get(i);
            if (match.contains(ing)) {
                ings.set(i, transformer.transform(this, match, ing, with));
                changed = true;
            }
        }
        if (match.contains(recipe.reagent)) {
            changed = true;
            recipe.reagent = transformer.transform(this, match, recipe.reagent, with);
        }
        return changed;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }
}
