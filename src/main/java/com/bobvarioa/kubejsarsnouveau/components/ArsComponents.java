package com.bobvarioa.kubejsarsnouveau.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentWithParent;
import dev.latvian.mods.kubejs.recipe.component.SimpleRecipeComponent;
import dev.latvian.mods.kubejs.recipe.match.Replaceable;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Optional;

public class ArsComponents {
    public static RecipeComponent<CrushItem> CRUSH_OUTPUT = new CrushItemComponent();
    public static RecipeComponent<Sound> SOUND = new SoundComponent();
    public static RecipeComponent<Color> COLOR = new ColorComponent();

    public static RecipeComponent<List<Ingredient>> INGREDIENT_LIST = new OptionalIngredientListComponent();

    public static class OptionalIngredientListComponent extends SimpleRecipeComponent<List<Ingredient>> implements RecipeComponentWithParent<List<Ingredient>> {

        public OptionalIngredientListComponent() {
            super("ingredient[]", Ingredient.CODEC.listOf(), TypeInfo.RAW_LIST);
        }

        @Override
        public RecipeComponent<List<Ingredient>> parentComponent() {
            return IngredientComponent.INGREDIENT.asList();
        }


        @Override
        public void validate(List<Ingredient> value) {
            // nop
        }
    }

    public record CrushItem(ItemStack stack, float chance, double maxRange) {
        public static Codec<CrushItem> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create((o) -> o.group(
                Codec.withAlternative(ItemStack.CODEC, ItemStack.SIMPLE_ITEM_CODEC).fieldOf("stack").forGetter(CrushItem::stack),
                Codec.FLOAT.fieldOf("chance").forGetter(CrushItem::chance),
                Codec.DOUBLE.fieldOf("maxRange").forGetter(CrushItem::maxRange)
        ).apply(o, CrushItem::new)));
    }

    public static class CrushItemComponent extends SimpleRecipeComponent<CrushItem> implements Replaceable {

        public CrushItemComponent() {
            super("crush_item", CrushItem.CODEC, TypeInfo.of(CrushItem.class));
        }

        @Override
        public boolean matches(Context cx, KubeRecipe recipe, CrushItem value, ReplacementMatchInfo match) {
            var mat = match.match();
            if (((Object) mat) instanceof Ingredient in) {
                if (in.test(value.stack)) {
                    return true;
                }
            }
            return super.matches(cx, recipe, value, match);
        }

        @Override
        public Object replaceThisWith(Context cx, Object with) {
            if (with instanceof ItemStack stack) {
                return new CrushItem(stack, 1.0f, 1);
            }
            return Replaceable.super.replaceThisWith(cx, with);
        }
    }


    public record Color(String id, int r, int g, int b) {
        public static Codec<Color> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create((o) -> o.group(
                Codec.STRING.fieldOf("id").forGetter(Color::id),
                Codec.INT.fieldOf("r").forGetter(Color::r),
                Codec.INT.fieldOf("g").forGetter(Color::g),
                Codec.INT.fieldOf("b").forGetter(Color::b)
            ).apply(o, Color::new)));
    }


    public static class ColorComponent extends SimpleRecipeComponent<Color>  {
        public ColorComponent() {
            super("color", Color.CODEC, TypeInfo.of(Color.class));
        }
    }
    
    public record SoundFamily(String id) {
        public static Codec<SoundFamily> FAMILY_CODEC = RecordCodecBuilder.create(o ->
                o.group(Codec.STRING.fieldOf("id").forGetter(SoundFamily::id)).apply(o, SoundFamily::new)
        );
    }

    public record Sound(Optional<String> family, Optional<Float> volume, Optional<Float> pitch) {
        public static Codec<Sound> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create((o) -> o.group(
                Codec.withAlternative(Codec.STRING, SoundFamily.FAMILY_CODEC, (v) -> v.id).optionalFieldOf("sound").forGetter(Sound::family),
                Codec.FLOAT.optionalFieldOf("volume").forGetter(Sound::volume),
                Codec.FLOAT.optionalFieldOf("pitch").forGetter(Sound::pitch)
        ).apply(o, Sound::new)));
    }

    public static class SoundComponent extends SimpleRecipeComponent<Sound>  {
        public SoundComponent() {
            super("sound", Sound.CODEC, TypeInfo.of(Sound.class));
        }
    }

}
