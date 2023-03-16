package com.bobvarioa.kubejsarsnoveau.recipes;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.setup.RecipeRegistry;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class EnchantingApparatusRecipeJS extends RecipeJS {
    private static final RecipeSerializer<EnchantingApparatusRecipe> serializer = RecipeRegistry.APPARATUS_SERIALIZER.get();
    private EnchantingApparatusRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new EnchantingApparatusRecipe(
            getOrCreateId(),
            parseItemInputList(args.get(0)),
            parseItemInput(args.get(1)),
            parseItemOutput(args.get(2)),
            args.getInt(3, 0),
            getBool(args.get(4))
        );
    }

    protected boolean getBool(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean bool) {
            return bool;
        }
        return (boolean) obj;
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
        return match.contains(recipe.result);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        if (match.contains(recipe.result)) {
            recipe.result = transformer.transform(this, match, recipe.result, with);
            return true;
        }
        return false;
    }
}
