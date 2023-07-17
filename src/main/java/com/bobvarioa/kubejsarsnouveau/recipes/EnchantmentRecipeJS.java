package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface EnchantmentRecipeJS {

    RecipeKey<InputItem[]> PEDESTAL_ITEMS = ArsComponents.INPUT_ITEM.asArray().key("pedestalItems");

    RecipeKey<String> ENCHANTMENT = StringComponent.ANY.key("enchantment");

    RecipeKey<Integer> LEVEL = NumberComponent.INT.key("level");
    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("sourceCost").alt("source");

    RecipeSchema SCHEMA = new RecipeSchema(PEDESTAL_ITEMS,ENCHANTMENT,  LEVEL, SOURCE);
}
