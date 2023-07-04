package com.bobvarioa.kubejsarsnouveau.recipes;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.Map;

public interface CrushRecipeJS {

    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");

    RecipeComponentBuilder CRUSH_OUTPUT =  new RecipeComponentBuilder(4)
            .add(StringComponent.ANY.key("item"))
            .add(NumberComponent.FLOAT.key("chance"))
            .add(NumberComponent.INT.key("count"))
            .add(NumberComponent.INT.key("maxRange"));

    RecipeKey<Map<String, Object>[]> CRUSH_OUTPUTS = CRUSH_OUTPUT.asArray().key("output");

    RecipeKey<Boolean> SKIP_BLOCK_PLACE = BooleanComponent.BOOLEAN.key("skip_block_place").optional(true);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, CRUSH_OUTPUTS, SKIP_BLOCK_PLACE);
}