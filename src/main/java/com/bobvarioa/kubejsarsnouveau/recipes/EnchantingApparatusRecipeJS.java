package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ItemComponentsExtra;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface EnchantingApparatusRecipeJS {

    RecipeKey<InputItem[]> PEDESTAL_ITEMS = ItemComponentsExtra.INPUT_ITEM_ARS.asArray().key("pedestalItems");

    RecipeKey<InputItem[]> REAGENT = ItemComponents.INPUT.asArray().key("reagent");

    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");

    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("sourceCost").alt("source").optional(0);

    RecipeKey<Boolean> KEEP_NBT = BooleanComponent.BOOLEAN.key("keepNbtOfReagent").alt("keepNbt").optional(false);

    RecipeSchema SCHEMA = new RecipeSchema(PEDESTAL_ITEMS, REAGENT, OUTPUT, SOURCE, KEEP_NBT);
}
