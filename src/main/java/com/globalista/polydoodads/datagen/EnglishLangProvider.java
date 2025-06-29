package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Doodad;
import com.globalista.polydoodads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends FabricLanguageProvider {
    protected EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder t) {

        for (Item doodad : Doodad.DOODADS) {
            String name = Helper.format(doodad.toString());
            t.add(doodad, name);
        }

        t.add(ModItems.CUT_AMETHYST, "Cut Amethyst");
        t.add(ModItems.CUT_DIAMOND, "Cut Diamond");
        t.add(ModItems.CUT_EMERALD, "Cut Emerald");
        t.add(ModItems.CUT_QUARTZ, "Cut Quartz");
        t.add(ModItems.CUT_GLOWSTONE, "Cut Glowstone");
        t.add(ModItems.CUT_GHAST_TEAR, "Cut Ghast Tear");
        t.add(ModItems.CUT_LAPIS_LAZULI, "Cut Lapis Lazuli");
        t.add(ModItems.CUT_REDSTONE, "Cut Redstone");
        t.add(ModItems.CUT_RESIN, "Cut Resin");

        t.add("itemGroup.polydoodads", "PolyDoodads");

    }
}
