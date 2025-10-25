package com.bobvarioa.kubejsarsnouveau.recipes;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.List;

public interface CasterTomeRecipeJS {

    RecipeKey<String> TOME_TYPE = StringComponent.OPTIONAL_STRING.key("tome_type", ComponentRole.OTHER).optional("ars_nouveau:caster_tome").alt("tomeType").alwaysWrite();
    RecipeKey<String> NAME = StringComponent.STRING.key("name", ComponentRole.OTHER);
    RecipeKey<String> FLAVOUR_TEXT = StringComponent.STRING.key("flavour_text", ComponentRole.OTHER).alt("description", "flavor_text", "flavorText", "flavourText");

    RecipeKey<ArsComponents.Color> COLOR = ArsComponents.COLOR.key("color", ComponentRole.OTHER);
    RecipeKey<List<String>> SPELL = StringComponent.STRING.instance().asList().key("spell", ComponentRole.OTHER);

    RecipeKey<ArsComponents.Sound> SOUND = ArsComponents.SOUND.key("sound", ComponentRole.OTHER).defaultOptional();

    RecipeSchema SCHEMA = new RecipeSchema(TOME_TYPE, NAME, SPELL, FLAVOUR_TEXT, COLOR,  SOUND).constructor(NAME, SPELL, FLAVOUR_TEXT, COLOR, SOUND).constructor(NAME);
}