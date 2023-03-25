package com.bobvarioa.kubejsarsnouveau.recipes;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantmentRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.setup.RecipeRegistry;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class GlyphRecipeJS extends RecipeJS {
    private static final RecipeSerializer<GlyphRecipe> serializer = RecipeRegistry.GLYPH_SERIALIZER.get();
    private GlyphRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new GlyphRecipe(
            new ResourceLocation("dummy"),
            parseItemOutput(args.get(0)),
            parseItemInputList(args.get(1)),
            args.getInt(2, 0)
        );
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
        var ings = recipe.inputs;
        for (var ing : ings) {
            if (match.contains(ing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        var ings = recipe.inputs;
        var changed = false;
        for (int i = 0; i < ings.size(); i++) {
            var ing = ings.get(i);
            if (match.contains(ing)) {
                ings.set(i, transformer.transform(this, match, ing, with));
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(recipe.output);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        if (match.contains(recipe.output)) {
            recipe.output = transformer.transform(this, match, recipe.output, with);
            return true;
        }
        return false;
    }
}
