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
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder t) {
        for (Item doodad : Doodad.DOODADS) {
            String name = Helper.format(doodad.toString());
            t.add(doodad, name);
        }

        t.add(Helper.getItem("cut_amethyst"), "Cut Amethyst");
        t.add(Helper.getItem("cut_diamond"), "Cut Diamond");
        t.add(Helper.getItem("cut_emerald"), "Cut Emerald");
        t.add(Helper.getItem("cut_quartz"), "Cut Quartz");
        t.add(Helper.getItem("cut_glowstone"), "Cut Glowstone");
        t.add(Helper.getItem("cut_ghast_tear"), "Cut Ghast Tear");
        t.add(Helper.getItem("cut_lapis_lazuli"), "Cut Lapis Lazuli");
        t.add(Helper.getItem("cut_redstone"), "Cut Redstone");

        t.add("itemGroup.polydoodads", "PolyDoodads");
    }
}
