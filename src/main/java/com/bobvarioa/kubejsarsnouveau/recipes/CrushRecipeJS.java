package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface CrushRecipeJS {

    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");

    RecipeKey<OutputItem[]> CRUSH_OUTPUTS = ArsComponents.CRUSH_OUTPUT.asArray().key("output");

    RecipeKey<Boolean> SKIP_BLOCK_PLACE = BooleanComponent.BOOLEAN.key("skip_block_place").optional(true);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, CRUSH_OUTPUTS, SKIP_BLOCK_PLACE);
}