package com.bobvarioa.kubejsarsnouveau.recipes;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface EnchantmentRecipeJS {

    RecipeKey<List<Ingredient>> PEDESTAL_ITEMS = IngredientComponent.INGREDIENT.asList().key("pedestalItems", ComponentRole.INPUT);

    RecipeKey<String> ENCHANTMENT = StringComponent.ANY.key("enchantment", ComponentRole.OTHER);

    RecipeKey<Integer> LEVEL = NumberComponent.INT.key("level", ComponentRole.OTHER);
    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("sourceCost", ComponentRole.OTHER).alt("source");

    RecipeSchema SCHEMA = new RecipeSchema(PEDESTAL_ITEMS,ENCHANTMENT,  LEVEL, SOURCE);
}
