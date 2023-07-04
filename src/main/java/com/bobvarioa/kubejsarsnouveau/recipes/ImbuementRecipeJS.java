package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ItemComponentsExtra;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface ImbuementRecipeJS {
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<OutputItem> OUTPUT_AND_COUNT = ItemComponentsExtra.OUTPUT_COUNT.key("output");
    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("source");
    RecipeKey<InputItem[]> PEDESTAL_ITEMS = ItemComponentsExtra.INPUT_ITEM_ARS.asArray().key("pedestalItems").optional(new InputItem[] {});

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, OUTPUT_AND_COUNT, SOURCE, PEDESTAL_ITEMS);
}