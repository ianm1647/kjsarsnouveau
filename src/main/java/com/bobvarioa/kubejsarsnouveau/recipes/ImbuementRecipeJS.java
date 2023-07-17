package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface ImbuementRecipeJS {
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<OutputItem> OUTPUT_AND_COUNT = ItemComponents.OUTPUT_ID_WITH_COUNT.key("output");
    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("source");
    RecipeKey<InputItem[]> PEDESTAL_ITEMS = ArsComponents.INPUT_ITEM.asArray().key("pedestalItems").optional(new InputItem[] {});

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, OUTPUT_AND_COUNT, SOURCE, PEDESTAL_ITEMS);
}