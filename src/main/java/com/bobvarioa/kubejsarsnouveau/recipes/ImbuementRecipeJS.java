package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeOptional;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface ImbuementRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.INGREDIENT.key("input", ComponentRole.INPUT);
    RecipeKey<ItemStack> OUTPUT_AND_COUNT = ItemStackComponent.ITEM_STACK.key("output", ComponentRole.OUTPUT);
    RecipeKey<Integer> SOURCE = NumberComponent.INT.key("source", ComponentRole.OTHER);
    RecipeKey<List<Ingredient>> PEDESTAL_ITEMS = ArsComponents.INGREDIENT_LIST
            .key("pedestalItems", ComponentRole.INPUT)
            .optional(List.of())
            .alwaysWrite();

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, OUTPUT_AND_COUNT, SOURCE, PEDESTAL_ITEMS);
}