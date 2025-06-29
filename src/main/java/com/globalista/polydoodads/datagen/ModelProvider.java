package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Doodad;
import com.globalista.polydoodads.item.Gem;
import com.globalista.polydoodads.item.Material;
import com.globalista.polydoodads.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator b) {
        // ...
    }


    @Override
    public void generateItemModels(ItemModelGenerator i) {

        for (String type : ModItems.TYPES) {
            for (Material material : Material.MATERIALS) {
                for (Gem gem : Gem.GEMS) {
                    Item doodad = Registries.ITEM.get(Helper.id(gem.getName() + "_" + material.getName() + "_" + type));
                    Identifier modelId = Models.GENERATED_TWO_LAYERS.upload(doodad, TextureMap.layered(Helper.id("item/base/" + material.getName() + "/" + type), Helper.id("item/gem/" + type + "/" + gem.getName())), i.modelCollector);
                    i.output.accept(doodad, ItemModels.basic(modelId));
                }

                Item doodad = Registries.ITEM.get(Helper.id(material.getName() + "_" + type));
                Identifier modelId = Models.GENERATED.upload(doodad, TextureMap.layer0(Helper.id("item/base/" + material.getName() + "/" + type)), i.modelCollector);
                i.output.accept(doodad, ItemModels.basic(modelId));
            }
        }

        for (Item item : ModItems.ITEMS) {
            i.register(item, Models.GENERATED);
        }


    }
}
