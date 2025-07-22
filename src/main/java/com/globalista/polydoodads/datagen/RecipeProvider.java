package com.globalista.polydoodads.datagen;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.item.Gem;
import com.globalista.polydoodads.item.Material;
import com.globalista.polydoodads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;


public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(net.minecraft.data.server.recipe.RecipeExporter exporter) {



        for (Material material : Material.MATERIALS) {

            Item ingot = Helper.getItem(material.getName() + "_ingot", true);

            if (!material.getName().contains("netherite")) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Helper.getItem(material.getName() + "_ring"), 1)
                        .pattern("NIN")
                        .pattern("I I")
                        .pattern("NIN")
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Helper.getItem(material.getName() + "_necklace"), 1)
                        .pattern("NIN")
                        .pattern("I I")
                        .pattern(" I ")
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Helper.getItem(material.getName() + "_anklet"), 1)
                        .pattern(" I ")
                        .pattern("I I")
                        .pattern("NIN")
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Helper.getItem(material.getName() + "_circlet"), 1)
                        .pattern(" N ")
                        .pattern("I I")
                        .pattern(" N ")
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Helper.getItem(material.getName() + "_mask"), 1)
                        .pattern("NIN")
                        .pattern(" I ")
                        .pattern("I I")
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', ingot)
                        .criterion(hasItem(ingot), conditionsFromItem(ingot))
                        .offerTo(exporter);
            } else {
                offerNetheriteUpgradeRecipe(exporter, Helper.getItem("gold_ring"), RecipeCategory.TOOLS, Helper.getItem("netherite_ring"));
                offerNetheriteUpgradeRecipe(exporter, Helper.getItem("gold_necklace"), RecipeCategory.TOOLS, Helper.getItem("netherite_necklace"));
                offerNetheriteUpgradeRecipe(exporter, Helper.getItem("gold_anklet"), RecipeCategory.TOOLS, Helper.getItem("netherite_anklet"));
                offerNetheriteUpgradeRecipe(exporter, Helper.getItem("gold_circlet"), RecipeCategory.TOOLS, Helper.getItem("netherite_circlet"));
                offerNetheriteUpgradeRecipe(exporter, Helper.getItem("gold_mask"), RecipeCategory.TOOLS, Helper.getItem("netherite_mask"));
            }

            for (Gem gem : Gem.GEMS) {
                for (String type : ModItems.TYPES) {

                    Item input = Helper.getItem(material.getName() + "_" + type);
                    Item result = Helper.getItem(gem.getName() + "_" + material.getName() + "_" + type);

                    String[] a = result.toString().split(":");
                    String b = a[1];

                    net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder.create(
                                    Ingredient.ofItems(ingot),
                                    Ingredient.ofItems(input),
                                    Ingredient.ofItems(Helper.getItem("cut_" + gem.getName())),
                                    RecipeCategory.TOOLS,
                                    result
                            )
                            .criterion(hasItem(input), conditionsFromPredicates(ItemPredicate.Builder.create().items(input)))
                            .offerTo(exporter, b + "_smithing");

                }
            }


        }

    }

    @Override
    public String getName() {
        return "PolyDoodadsRecipeProvider";
    }
}
