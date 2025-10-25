package com.bobvarioa.kubejsarsnouveau.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.filter.RecipeMatchContext;
import dev.latvian.mods.kubejs.recipe.match.Replaceable;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

public class ArsComponents {
    public static RecipeComponentType<CrushItem> CRUSH_OUTPUT = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "crush_item"), CrushItemComponent::new);
    public static RecipeComponentType<Sound> SOUND = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "sound"), SoundComponent::new);
    public static RecipeComponentType<Color> COLOR = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "color"), ColorComponent::new);

    public record CrushItem(ItemStack stack, float chance, double maxRange) {
        public static Codec<CrushItem> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create((o) -> o.group(
                Codec.withAlternative(ItemStack.CODEC, ItemStack.SIMPLE_ITEM_CODEC).fieldOf("stack").forGetter(CrushItem::stack),
                Codec.FLOAT.fieldOf("chance").forGetter(CrushItem::chance),
                Codec.DOUBLE.fieldOf("maxRange").forGetter(CrushItem::maxRange)
        ).apply(o, CrushItem::new)));
    }

    public static class CrushItemComponent extends SimpleRecipeComponent<CrushItem> implements Replaceable {

        public CrushItemComponent(RecipeComponentType<?> type) {
            super(type, CrushItem.CODEC, TypeInfo.of(CrushItem.class));
        }

        @Override
        public boolean matches(RecipeMatchContext cx, CrushItem value, ReplacementMatchInfo match) {
            var mat = match.match();
            if (((Object) mat) instanceof Ingredient in) {
                if (in.test(value.stack)) {
                    return true;
                }
            }
            return super.matches(cx, value, match);
        }

        @Override
        public CrushItem replace(RecipeScriptContext cx, CrushItem original, ReplacementMatchInfo match, Object with) {
            if (with instanceof ItemStack stack) {
                return new CrushItem(stack, 1.0f, 1);
            }

            return matches(cx, original, match) ? (CrushItem) Replaceable.super.replaceThisWith(cx, with) : original;
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
        public ColorComponent(RecipeComponentType<?> type) {
            super(type, Color.CODEC, TypeInfo.of(Color.class));
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
        public SoundComponent(RecipeComponentType<?> type) {
            super(type, Sound.CODEC, TypeInfo.of(Sound.class));
        }
    }

}
