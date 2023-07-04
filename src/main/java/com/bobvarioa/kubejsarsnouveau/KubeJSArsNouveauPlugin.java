package com.bobvarioa.kubejsarsnouveau;

import com.bobvarioa.kubejsarsnouveau.recipes.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import net.minecraft.resources.ResourceLocation;

public class KubeJSArsNouveauPlugin extends KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(new ResourceLocation("ars_nouveau:enchanting_apparatus"), EnchantingApparatusRecipeJS.SCHEMA);
        event.register(new ResourceLocation("ars_nouveau:enchantment"), EnchantmentRecipeJS.SCHEMA);
        event.register(new ResourceLocation("ars_nouveau:crush"), CrushRecipeJS.SCHEMA);
        event.register(new ResourceLocation("ars_nouveau:imbuement"), ImbuementRecipeJS.SCHEMA);
        event.register(new ResourceLocation("ars_nouveau:glyph"), GlyphRecipeJS.SCHEMA);
        event.register(new ResourceLocation("ars_nouveau:caster_tome"), CasterTomeRecipeJS.SCHEMA);
    }
}
