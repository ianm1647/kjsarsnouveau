package com.bobvarioa.kubejsarsnoveau;

import com.bobvarioa.kubejsarsnoveau.recipes.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.minecraft.resources.ResourceLocation;

public class KubeJSArsNouveauPlugin extends KubeJSPlugin {
    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(new ResourceLocation("ars_nouveau:enchanting_apparatus"), EnchantingApparatusRecipeJS::new);
        event.register(new ResourceLocation("ars_nouveau:enchantment"), EnchantmentRecipeJS::new);
        event.register(new ResourceLocation("ars_nouveau:crush"), CrushRecipeJS::new);
        event.register(new ResourceLocation("ars_nouveau:imbuement"), ImbuementRecipeJS::new);
        event.register(new ResourceLocation("ars_nouveau:glyph"), GlyphRecipeJS::new);
        event.register(new ResourceLocation("ars_nouveau:caster_tome"), CasterTomeRecipeJS::new);
    }
}
