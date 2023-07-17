package com.bobvarioa.kubejsarsnouveau.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.registry.KubeJSRegistries;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.world.item.ItemStack;

public class ArsComponents {

    public static RecipeComponent<InputItem> INPUT_ITEM = new RecipeComponentWithParent<InputItem>() {
        @Override
        public RecipeComponent<InputItem> parentComponent() {
            return ItemComponents.INPUT;
        }

        @Override
        public JsonElement write(RecipeJS recipe, InputItem value) {
            var json = new JsonObject();
            json.add("item", recipe.writeInputItem(value));
            return json;
        }

        @Override
        public InputItem read(RecipeJS recipe, Object from) {
            var json = MapJS.json(from);
            if (json == null) {
                return recipe.readInputItem(from);
            }
            JsonElement item = json.get("item");
            return recipe.readInputItem(item == null ? from : item);
        }
    };

    public static class CrushOutput extends OutputItem {

        protected CrushOutput(OutputItem out) {
            super(out.item, out.chance);
        }

        public int maxRange = 1;
    }

    public static RecipeComponent<CrushOutput> CRUSH_OUTPUT = new RecipeComponent<CrushOutput>() {
        @Override
        public Class<?> componentClass() {
            return CrushOutput.class;
        }

        @Override
        public ComponentRole role() {
            return ComponentRole.OUTPUT;
        }

        @Override
        public String componentType() {
            return "ars_output_item_range";
        }


        @Override
        public JsonElement write(RecipeJS recipe, CrushOutput value) {
            var json = new JsonObject();
            json.addProperty("item", KubeJSRegistries.items().getId(value.item.getItem()).toString());
            json.addProperty("count", value.item.getCount());
            json.addProperty("chance", value.getChance());
            json.addProperty("maxRange", value.maxRange);
            return json;
        }

        @Override
        public CrushOutput read(RecipeJS recipe, Object from) {
            var json = MapJS.of(from);

            if (json != null && json.containsKey("item")) {
                var out = new CrushOutput(ItemComponents.OUTPUT.read(recipe, json.get("item")));

                if (json.containsKey("maxRange") || json.containsKey("maxRolls")) {
                    Object num = json.get("maxRange");
                    if (num == null) {
                        num = json.get("maxRolls");
                    }
                    out.maxRange = ((Number)num).intValue();
                }

                return out;
            }

            return new CrushOutput(ItemComponents.OUTPUT.read(recipe, from));
        }

        @Override
        public boolean hasPriority(RecipeJS recipe, Object from) {
            return ItemComponents.OUTPUT.hasPriority(recipe, from);
        }

        @Override
        public boolean isInput(RecipeJS recipe, CrushOutput value, ReplacementMatch match) {
            return ItemComponents.OUTPUT.isInput(recipe, value, match);
        }

        @Override
        public CrushOutput replaceInput(RecipeJS recipe, CrushOutput original, ReplacementMatch match, InputReplacement with) {
            return new CrushOutput(ItemComponents.OUTPUT.replaceInput(recipe, original, match, with));
        }

        @Override
        public boolean isOutput(RecipeJS recipe, CrushOutput value, ReplacementMatch match) {
            return ItemComponents.OUTPUT.isOutput(recipe, value, match);
        }

        @Override
        public CrushOutput replaceOutput(RecipeJS recipe, CrushOutput original, ReplacementMatch match, OutputReplacement with) {
            return new CrushOutput(ItemComponents.OUTPUT.replaceOutput(recipe, original, match, with));
        }

        @Override
        public String checkEmpty(RecipeKey<CrushOutput> key, CrushOutput value) {
            // key never gets checked anyways
            return ItemComponents.OUTPUT.checkEmpty(null, value);
        }
    };
}
