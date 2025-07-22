package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Doodad;
import com.globalista.polydoodads.item.Gem;
import com.globalista.polydoodads.item.Material;
import com.globalista.polydoodads.item.ModItems;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;

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
                    Identifier id = Helper.id("item/" + gem.getName() + "_" + material.getName() + "_" + type);

                    Models.GENERATED_TWO_LAYERS.upload(id,
                            TextureMap.layered(Helper.id("item/base/" + material.getName() + "/" + type), Helper.id("item/gem/" + type + "/" + gem.getName())),
                            i.writer);

                }

                Identifier id = Helper.id("item/" + material.getName() + "_" + type);

                Models.GENERATED.upload(id, TextureMap.layer0(Helper.id("item/base/" + material.getName() + "/" + type)), i.writer);

            }
        }

        for (Item item : ModItems.ITEMS) {
            i.register(item, Models.GENERATED);
        }


    }


    private JsonObject createJson(Identifier id, Map<TextureKey, Identifier> textures) {
        JsonObject jsonObject = new JsonObject();
        if (!textures.isEmpty()) {
            JsonObject jsonObject2 = new JsonObject();
            textures.forEach((textureKey, texture) -> jsonObject2.addProperty(textureKey.getName(), texture.toString()));
            jsonObject.add("textures", jsonObject2);
        }

        return jsonObject;
    }
}
