package com.bobvarioa.kubejsarsnouveau.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.registry.KubeJSRegistries;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import dev.latvian.mods.kubejs.util.TinyMap;

import java.util.Map;

public class ItemComponentsExtra {

    public static RecipeComponent<InputItem> INPUT_ITEM_ARS = new RecipeComponentWithParent<InputItem>() {
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
}
