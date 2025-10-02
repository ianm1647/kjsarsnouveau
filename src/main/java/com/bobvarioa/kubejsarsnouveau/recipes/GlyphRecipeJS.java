package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface GlyphRecipeJS {

    RecipeKey<List<Ingredient>> INPUT_ITEMS = IngredientComponent.INGREDIENT.asList().key("inputs", ComponentRole.INPUT);
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.key("output", ComponentRole.OUTPUT);

    RecipeKey<Integer> EXPERIENCE = NumberComponent.INT.key("exp", ComponentRole.OTHER).alt("experience", "xp").optional(0);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT_ITEMS, EXPERIENCE);
}
