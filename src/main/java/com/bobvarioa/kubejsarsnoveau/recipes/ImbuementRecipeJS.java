package com.bobvarioa.kubejsarsnoveau.recipes;

import com.hollingsworth.arsnouveau.common.crafting.recipes.CrushRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.setup.RecipeRegistry;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ImbuementRecipeJS extends RecipeJS {
    private static final RecipeSerializer<ImbuementRecipe> serializer = RecipeRegistry.IMBUEMENT_SERIALIZER.get();
    private ImbuementRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new ImbuementRecipe(
            getOrCreateId(),
            parseItemInput(args.get(0)),
            parseItemOutput(args.get(1)),
            args.getInt(2, 0),
            parseItemInputList(args.get(3))
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
        var ings = recipe.pedestalItems;
        for (Ingredient ing : ings) {
            if (match.contains(ing)) {
                return true;
            }
        }
        return match.contains(recipe.input);
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
        if (match.contains(recipe.input)) {
            changed = true;
        recipe = new ImbuementRecipe(
                getOrCreateId(),
                transformer.transform(this, match, recipe.input, with),
                recipe.output,
                recipe.source,
                recipe.pedestalItems
            );
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
            recipe = new ImbuementRecipe(
                    getOrCreateId(),
                    recipe.input,
                    transformer.transform(this, match, recipe.output, with),
                    recipe.source,
                    recipe.pedestalItems
            );
            return true;
        }
        return false;
    }
}
