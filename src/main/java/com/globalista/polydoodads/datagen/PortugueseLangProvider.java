package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Doodad;
import com.globalista.polydoodads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class PortugueseLangProvider extends FabricLanguageProvider {
    protected PortugueseLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "pt_br", registryLookup);
    }

    public static String translateItemName(String originalName) {
        String[] parts = originalName.split(" ");
        int len = parts.length;

        String typeEn = parts[len - 1];
        String typePt = TRANSLATION.getOrDefault(typeEn, typeEn);

        String materialEn = null;
        String materialPt = null;
        int materialIndex = -1;

        for (int i = len - 2; i >= 0; i--) {
            StringBuilder candidate = new StringBuilder();
            for (int j = i; j < len - 1; j++) {
                if (j > i) candidate.append(" ");
                candidate.append(parts[j]);
            }
            String candidateStr = candidate.toString();
            if (TRANSLATION.containsKey(candidateStr)) {
                materialEn = candidateStr;
                materialPt = TRANSLATION.get(candidateStr);
                materialIndex = i;
                break;
            }
        }

        String gemPt = null;
        if (materialIndex > 0) {
            StringBuilder gemBuilder = new StringBuilder();
            for (int i = 0; i < materialIndex; i++) {
                if (i > 0) gemBuilder.append(" ");
                gemBuilder.append(parts[i]);
            }
            String gemEn = gemBuilder.toString();
            gemPt = TRANSLATION.getOrDefault(gemEn, gemEn);
        }

        String result = typePt + " de " + materialPt;
        if (gemPt != null) {
            result += " e " + gemPt;
        }

        return result;
    }


    private static final HashMap<String, String> TRANSLATION = new HashMap<>();
    static {
        TRANSLATION.put("Copper", "Cobre");
        TRANSLATION.put("Iron", "Ferro");
        TRANSLATION.put("Gold", "Ouro");
        TRANSLATION.put("Netherite", "Netherita");
        TRANSLATION.put("Quartz", "Quartzo");
        TRANSLATION.put("Diamond", "Diamante");
        TRANSLATION.put("Emerald", "Esmeralda");
        TRANSLATION.put("Lapis Lazuli", "Lápis-lazúli");
        TRANSLATION.put("Amethyst", "Ametista");
        TRANSLATION.put("Redstone", "Redstone");
        TRANSLATION.put("Resin", "Resina");
        TRANSLATION.put("Glowstone", "Glowstone");
        TRANSLATION.put("Ghast Tear", "Lágrima de Ghast");
        TRANSLATION.put("Ring", "Anel");
        TRANSLATION.put("Circlet", "Diadema");
        TRANSLATION.put("Anklet", "Tornozeleira");
        TRANSLATION.put("Necklace", "Colar");
        TRANSLATION.put("Mask", "Máscara");

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder t) {

        for (Item doodad : Doodad.DOODADS) {
            String name = translateItemName(Helper.format(doodad.toString()));
            t.add(doodad, name);
        }

        t.add(ModItems.CUT_AMETHYST, "Ametista Lapidada");
        t.add(ModItems.CUT_DIAMOND, "Diamante Lapidado");
        t.add(ModItems.CUT_EMERALD, "Esmeralda Lapidada");
        t.add(ModItems.CUT_QUARTZ, "Quartzo Lapidado");
        t.add(ModItems.CUT_GLOWSTONE, "Glowstone Lapidada");
        t.add(ModItems.CUT_GHAST_TEAR, "Lágrima de Ghast Lapidada");
        t.add(ModItems.CUT_LAPIS_LAZULI, "Lápis-lazúli Lapidado");
        t.add(ModItems.CUT_REDSTONE, "Redstone Lapidada");
        t.add(ModItems.CUT_RESIN, "Resina Lapidada");

        t.add("itemGroup.polydoodads", "PolyDoodads");

    }
}
