package com.bobvarioa.kubejsarsnouveau;

import com.bobvarioa.kubejsarsnouveau.components.ArsComponents;
import com.bobvarioa.kubejsarsnouveau.recipes.*;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import net.minecraft.resources.ResourceLocation;

public class KubeJSArsNouveauPlugin implements dev.latvian.mods.kubejs.plugin.KubeJSPlugin {

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(ArsComponents.INGREDIENT_LIST);
        registry.register(ArsComponents.CRUSH_OUTPUT);
        registry.register(ArsComponents.COLOR);
        registry.register(ArsComponents.SOUND);
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "enchanting_apparatus"), EnchantingApparatusRecipeJS.SCHEMA);
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "enchantment"), EnchantmentRecipeJS.SCHEMA);
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "crush"), CrushRecipeJS.SCHEMA);
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "imbuement"), ImbuementRecipeJS.SCHEMA);
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "glyph"), GlyphRecipeJS.SCHEMA);
        registry.register(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "caster_tome"), CasterTomeRecipeJS.SCHEMA);
    }
}
