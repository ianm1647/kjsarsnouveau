package com.bobvarioa.kubejsarsnouveau.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.util.MapJS;

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


    public static RecipeComponent<OutputItem> CRUSH_OUTPUT = new RecipeComponentWithParent<OutputItem>() {

        @Override
        public RecipeComponent<OutputItem> parentComponent() {
            return ItemComponents.OUTPUT;
        }

        @Override
        public String componentType() {
            return "ars_output_item_range";
        }


        @Override
        public JsonElement write(RecipeJS recipe, OutputItem value) {
            var json = RecipeComponentWithParent.super.write(recipe, value).getAsJsonObject();
            if (value.rolls != null) json.addProperty("maxRange", value.rolls.getMaxValue());
            return json;
        }
    };
}
