package com.bobvarioa.kubejsarsnouveau.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.ItemMatch;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.ReplacementMatch;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.registry.KubeJSRegistries;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import dev.latvian.mods.kubejs.util.TinyMap;

import java.util.Map;

public class ItemComponentsExtra {
    public static RecipeComponent<OutputItem> OUTPUT_COUNT = new RecipeComponentWithParent<>() {
        @Override
        public RecipeComponent<OutputItem> parentComponent() {
            return ItemComponents.OUTPUT;
        }

        @Override
        public void writeToJson(RecipeComponentValue<OutputItem> value, JsonObject json) {
            json.addProperty(value.key.name, KubeJSRegistries.items().getId(value.value.item.getItem()).toString());
            json.addProperty("count", value.value.item.getCount());
        }

        @Override
        public OutputItem readFromJson(RecipeJS recipe, RecipeKey<OutputItem> key, JsonObject json) {
            var item = RecipeComponentWithParent.super.readFromJson(recipe, key, json);

            if (item != null && json.has("count")) {
                item.item.setCount(json.get("count").getAsInt());
            }

            return item;
        }

        @Override
        public String toString() {
            return parentComponent().toString();
        }
    };

    public static RecipeComponent<InputItem> INPUT_ITEM_ARS = new RecipeComponent<InputItem>() {
        @Override
        public Class<?> componentClass() {
            return InputItem.class;
        }

        public ComponentRole role() {
            return ComponentRole.OUTPUT;
        }

        @Override
        public String componentType() {
            return "input_item_ars";
        }

        @Override
        public JsonElement write(RecipeJS recipe, InputItem value) {
            var json = new JsonObject();
            json.add("item", recipe.writeInputItem(value));
            return json;
        }

        @Override
        public InputItem read(RecipeJS recipe, Object from) {

            if (from instanceof String) {
                return recipe.readInputItem(from);
            }

            var json = MapJS.json(from);
            JsonElement item = json.get("item");
            return recipe.readInputItem(item == null ? from : item);
        }

        @Override
        public boolean isInput(RecipeJS recipe, InputItem value, ReplacementMatch match) {
            return match instanceof ItemMatch m && !value.isEmpty() && m.contains(value.ingredient);
        }

        @Override
        public String checkEmpty(RecipeKey<InputItem> key, InputItem value) {
            if (value.isEmpty()) {
                return "Ingredient '" + key.name + "' can't be empty!";
            }

            return "";
        }

        @Override
        public RecipeComponent<TinyMap<Character, InputItem>> asPatternKey() {
            return MapRecipeComponent.ITEM_PATTERN_KEY;
        }

        public boolean hasPriority(RecipeJS recipe, Object from) {
            return recipe.inputItemHasPriority(from);
        }

        @Override
        public String toString() {
            return componentType();
        }
    };
}
