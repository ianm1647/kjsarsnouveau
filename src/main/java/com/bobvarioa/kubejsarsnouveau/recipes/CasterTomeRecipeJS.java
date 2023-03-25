package com.bobvarioa.kubejsarsnouveau.recipes;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.sound.ConfiguredSpellSound;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.common.tomes.CasterTomeData;
import com.hollingsworth.arsnouveau.setup.RecipeRegistry;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.ArrayList;
import java.util.List;

public class CasterTomeRecipeJS extends RecipeJS {
    private static final RecipeSerializer<CasterTomeData> serializer = RecipeRegistry.CASTER_TOME_SERIALIZER.get();
    private CasterTomeData recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new CasterTomeData(
                new ResourceLocation("dummy"),
                args.getString(0, ""),
                parseResourceList(args.get(1)),
                new ResourceLocation("ars_nouveau:caster_tome"),
                args.getString(2, ""),
                args.getInt(3, 0),
                parseConfiguredSound(args.get(4))
        );
    }

    protected SpellSound getSpellSound(Object key) {
        return ArsNouveauAPI.getInstance().getSpellSoundsRegistry().get(getAsID(key));
    }

    protected ConfiguredSpellSound parseConfiguredSound(Object obj) {
        if (obj instanceof ConfiguredSpellSound sound) {
            return sound;
        }

        if (obj instanceof SpellSound sound) {
            return new ConfiguredSpellSound(sound);
        }

        if (obj instanceof String str) {
            return new ConfiguredSpellSound(getSpellSound(str)) ;
        }

        var map = MapJS.json(obj);
        if (map == null) {
            return null;
        }
        return new ConfiguredSpellSound(
            getSpellSound(map.get("family").getAsString()),
            map.get("volume").getAsFloat(),
            map.get("pitch").getAsFloat()
        );
    }

    protected List<ResourceLocation> parseResourceList(Object obj) {
        List<?> list = ListJS.of(obj);
        if (list == null) {
            return null;
        }
        List<ResourceLocation> resList = new ArrayList<>();
        for (var ele : list) {
            resList.add(getAsID(ele));
        }
        return resList;
    }

    protected ResourceLocation getAsID(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof ResourceLocation rl)
            return rl;
        return new ResourceLocation(o.toString());
    }

    @Override
    public void deserialize() {
        recipe = serializer.fromJson(new ResourceLocation("kubejs:empty"), json);
    }

    @Override
    public void serialize() {
        for (var entry : recipe.toJson().getAsJsonObject().entrySet()) {
            json.add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return false;
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }
}
