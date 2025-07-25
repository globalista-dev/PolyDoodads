package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Gem;
import com.globalista.polydoodads.item.Material;
import com.globalista.polydoodads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (Material material : Material.MATERIALS) {
            String name = material.getName();
            Item ingot = material.getItem();

            boolean isNetherite = name.contains("netherite");

            if (!isNetherite) {
                registerShaped(name, "ring", ingot, exporter, "NIN", "I I", "NIN");
                registerShaped(name, "necklace", ingot, exporter, "NIN", "I I", " I ");
                registerShaped(name, "anklet", ingot, exporter, " I ", "I I", "NIN");
                registerShaped(name, "circlet", ingot, exporter, " N ", "I I", " N ");
                registerShaped(name, "mask", ingot, exporter, "NIN", " I ", "I I");
            } else {
                upgradeToNetherite("ring", exporter);
                upgradeToNetherite("necklace", exporter);
                upgradeToNetherite("anklet", exporter);
                upgradeToNetherite("circlet", exporter);
                upgradeToNetherite("mask", exporter);
            }

            for (Gem gem : Gem.GEMS) {
                for (String type : ModItems.TYPES) {
                    Item base = Helper.getItem(name + "_" + type);
                    Item cutGem = Helper.getItem("cut_" + gem.getName());
                    Item result = Helper.getItem(gem.getName() + "_" + name + "_" + type);

                    SmithingTransformRecipeJsonBuilder.create(
                                    Ingredient.ofItems(base),
                                    Ingredient.ofItems(cutGem),
                                    Ingredient.ofItems(ingot),
                                    RecipeCategory.TOOLS,
                                    result
                            ).criterion("has_" + base, conditionsFromItem(base))
                            .offerTo(exporter, result.toString().replace(":", "_") + "_smithing");
                }
            }
        }
    }

    private void registerShaped(String materialName, String type, Item ingot, Consumer<RecipeJsonProvider> exporter, String... pattern) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(
                RecipeCategory.TOOLS,
                Helper.getItem(materialName + "_" + type)
        );

        for (String line : pattern) {
            builder.pattern(line);
        }

        builder.input('I', ingot)
                .input('N', Items.GOLD_NUGGET)
                .criterion("has_" + ingot, conditionsFromItem(ingot))
                .offerTo(exporter);
    }

    private void upgradeToNetherite(String type, Consumer<RecipeJsonProvider> exporter) {
        offerNetheriteUpgradeRecipe(
                exporter,
                Helper.getItem("gold_" + type),
                RecipeCategory.TOOLS,
                Helper.getItem("netherite_" + type)
        );
    }

    @Override
    public String getName() {
        return "PolyDoodadsRecipeProvider";
    }
}
