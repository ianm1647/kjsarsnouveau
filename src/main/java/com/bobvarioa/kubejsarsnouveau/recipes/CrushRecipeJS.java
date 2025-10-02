package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface CrushRecipeJS {

    RecipeKey<Ingredient> INPUT = IngredientComponent.INGREDIENT.key("input", ComponentRole.INPUT);

    RecipeKey<List<ArsComponents.CrushItem>> CRUSH_OUTPUTS = ArsComponents.CRUSH_OUTPUT.asList().key("output", ComponentRole.INPUT);

    RecipeKey<Boolean> SKIP_BLOCK_PLACE = BooleanComponent.BOOLEAN.key("skip_block_place", ComponentRole.OTHER).optional(true);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, CRUSH_OUTPUTS, SKIP_BLOCK_PLACE);
}