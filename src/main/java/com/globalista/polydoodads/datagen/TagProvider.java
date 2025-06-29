package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Doodad;
import dev.emi.trinkets.api.Trinket;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagProvider extends FabricTagProvider<Item> {
	public TagProvider(FabricDataOutput output, CompletableFuture< RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    public static final TagKey<Item> RINGS = TagKey.of(RegistryKeys.ITEM, Helper.id("rings"));
    public static final TagKey<Item> NECKLACES = TagKey.of(RegistryKeys.ITEM, Helper.id("necklaces"));
    public static final TagKey<Item> ANKLETS = TagKey.of(RegistryKeys.ITEM, Helper.id("anklets"));
    public static final TagKey<Item> CIRCLETS = TagKey.of(RegistryKeys.ITEM, Helper.id("circlets"));
    public static final TagKey<Item> MASKS = TagKey.of(RegistryKeys.ITEM, Helper.id("masks"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "hand/ring"))).addTag(RINGS.id());
        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "offhand/ring"))).addTag(RINGS.id());
        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "chest/necklace"))).addTag(NECKLACES.id());
        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "feet/aglet"))).addTag(ANKLETS.id());
        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "head/hat"))).addTag(CIRCLETS.id());
        getTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("trinkets", "head/face"))).addTag(MASKS.id());

        for (Item doodad : Doodad.DOODADS) {

            Identifier id = Identifier.of(doodad.toString());

            if(doodad.toString().contains("ring")) { getTagBuilder(RINGS).add(id); }
            if(doodad.toString().contains("necklace")) { getTagBuilder(NECKLACES).add(id); }
            if(doodad.toString().contains("anklet")) { getTagBuilder(ANKLETS).add(id); }
            if(doodad.toString().contains("circlet")) { getTagBuilder(CIRCLETS).add(id); }
            if(doodad.toString().contains("mask")) { getTagBuilder(MASKS).add(id); }

        }


    }
}
