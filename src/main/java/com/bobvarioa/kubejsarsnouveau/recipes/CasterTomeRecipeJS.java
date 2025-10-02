package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import static dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilder.Key;
import static dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilder.Value;

import java.util.List;
import java.util.Map;

public interface CasterTomeRecipeJS {

    RecipeKey<String> TOME_TYPE = StringComponent.ANY.key("tome_type", ComponentRole.OTHER).optional("ars_nouveau:caster_tome").alt("tomeType").alwaysWrite();
    RecipeKey<String> NAME = StringComponent.ANY.key("name", ComponentRole.OTHER);
    RecipeKey<String> FLAVOUR_TEXT = StringComponent.ANY.key("flavour_text", ComponentRole.OTHER).alt("description", "flavor_text", "flavorText", "flavourText");

    RecipeKey<ArsComponents.Color> COLOR = ArsComponents.COLOR.key("color", ComponentRole.OTHER);
    RecipeKey<List<String>> SPELL = StringComponent.ANY.asList().key("spell", ComponentRole.OTHER);

    RecipeKey<ArsComponents.Sound> SOUND = ArsComponents.SOUND.key("sound", ComponentRole.OTHER).defaultOptional();

    RecipeSchema SCHEMA = new RecipeSchema(TOME_TYPE, NAME, SPELL, FLAVOUR_TEXT, COLOR,  SOUND).constructor(NAME, SPELL, FLAVOUR_TEXT, COLOR, SOUND).constructor(NAME);
}