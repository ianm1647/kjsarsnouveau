package com.bobvarioa.kubejsarsnouveau.recipes;

import com.hollingsworth.arsnouveau.common.crafting.recipes.CrushRecipe;
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

public class CrushRecipeJS extends RecipeJS {
    private static final RecipeSerializer<CrushRecipe> serializer = RecipeRegistry.CRUSH_SERIALIZER.get();
    private CrushRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new CrushRecipe(
                new ResourceLocation("dummy"),
            parseItemInput(args.get(0)),
            parseCrushOutputs(args.get(1)),
            getBool(args.get(2))
        );
    }

    protected List<CrushRecipe.CrushOutput> parseCrushOutputs(Object object) {
        var list = ListJS.orSelf(object);
        if (list == null) {
            return null;
        }

        var clist = new ArrayList<CrushRecipe.CrushOutput>();
        for (var ele : list) {
            var map = MapJS.json(ele);
            if (map == null) continue;
            var maxRange = 1;
            if (map.get("maxRange") != null) {
                maxRange = map.get("maxRange").getAsInt();
            }
            clist.add(new CrushRecipe.CrushOutput(
                parseItemOutput(map.get("item")),
                map.get("chance").getAsFloat(),
                maxRange
            ));
        }
        return clist;
    }

    protected boolean getBool(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean bool) {
            return bool;
        }
        return (boolean) obj;
    }


    @Override
    public void deserialize() {
        recipe = serializer.fromJson(new ResourceLocation("kubejs:empty"), json);
    }

    @Override
    public void serialize() {
        for (var entry : recipe.asRecipe().getAsJsonObject().entrySet()) {
            json.add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(recipe.input);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (match.contains(recipe.input)) {
            recipe = new CrushRecipe(
                getOrCreateId(),
                transformer.transform(this, match, recipe.input, with),
                recipe.outputs,
                recipe.shouldSkipBlockPlace()
            );
        }
        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        for (var ele : recipe.outputs){
            if (match.contains(ele.stack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        var outs = recipe.outputs;
        var changed = false;
        for (int i = 0; i < outs.size(); i++) {
            var out = outs.get(i);
            if (match.contains(out.stack)) {
                changed = true;
                outs.set(i, new CrushRecipe.CrushOutput(
                        transformer.transform(this, match, out.stack, with),
                        out.chance,
                        out.maxRange
                ));
            }
        }
        return changed;
    }
}
