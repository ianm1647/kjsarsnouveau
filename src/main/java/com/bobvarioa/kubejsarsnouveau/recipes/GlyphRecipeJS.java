package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ItemComponentsExtra;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface GlyphRecipeJS {

    RecipeKey<InputItem[]> INPUT_ITEMS = ItemComponentsExtra.INPUT_ITEM_ARS.asArray().key("inputItems");
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT_ID_WITH_COUNT.key("output");

    RecipeKey<Integer> EXPERIENCE = NumberComponent.INT.key("exp").alt("experience", "xp").optional(0);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT_ITEMS, EXPERIENCE);
}
