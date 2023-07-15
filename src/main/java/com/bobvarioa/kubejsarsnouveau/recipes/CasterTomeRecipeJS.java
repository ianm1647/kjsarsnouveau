package com.bobvarioa.kubejsarsnouveau.recipes;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilder;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.Map;

public interface CasterTomeRecipeJS {

    RecipeKey<String> TOME_TYPE = StringComponent.ANY.key("tome_type").optional("ars_nouveau:caster_tome").alt("tomeType");
    RecipeKey<String> NAME = StringComponent.ANY.key("name");
    RecipeKey<String> FLAVOUR_TEXT = StringComponent.ANY.key("flavour_text").alt("description", "flavor_text", "flavorText", "flavourText");
    RecipeKey<Long> COLOR = NumberComponent.LONG.key("color");
    RecipeKey<String[]> SPELL = StringComponent.ANY.asArray().key("spell");

    RecipeComponentBuilder SOUND_COMPONENT = RecipeComponent.builder()
            .add(StringComponent.ANY.key("family").optional("default"))
            .add(NumberComponent.FLOAT.key("volume"))
            .add(NumberComponent.FLOAT.key("pitch"));

    RecipeKey<RecipeComponentBuilder.RCBHolder[]> SOUND = SOUND_COMPONENT.key("sound");

    RecipeSchema SCHEMA = new RecipeSchema(TOME_TYPE, NAME, SPELL, FLAVOUR_TEXT, COLOR,  SOUND).constructor(NAME, SPELL, FLAVOUR_TEXT, COLOR, SOUND).constructor(NAME);
}