package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {

    public static ArrayList<String> TYPES = new ArrayList<>();
    public static ArrayList<Item> ITEMS = new ArrayList<>();
    static {
        TYPES.add("ring");
        TYPES.add("necklace");
        TYPES.add("anklet");
        TYPES.add("circlet");
        TYPES.add("mask");
    }

    static {
        for (Material material : Material.MATERIALS) {
            for (String type : TYPES) {

                if(material.getSecondaryModifier() == null) {
                    Doodad.create(material.getName() + "_" + type, List.of(
                            material.getModifier())
                    );
                } else {
                    Doodad.create(material.getName() + "_" + type, List.of(
                            material.getModifier(),
                            material.getSecondaryModifier())
                    );
                }


                for (Gem gem : Gem.GEMS) {
                    if (material.getSecondaryModifier() == null) {
                        Doodad.create(gem.getName() + "_" + material.getName() + "_" + type, List.of(
                                new EntityAttributeModifier(gem.getAttribute(),gem.getValue() * material.getBonus(), gem.getOperation()),
                                material.getModifier())
                        );

                    } else if (material.getName().contains("netherite")) {
                        Doodad.create(gem.getName() + "_" + material.getName() + "_" + type, Rarity.RARE, List.of(
                                new EntityAttributeModifier(gem.getAttribute(),gem.getValue() * material.getBonus(), gem.getOperation()),
                                material.getModifier(),
                                material.getSecondaryModifier())
                        );
                    } else {
                        Doodad.create(gem.getName() + "_" + material.getName() + "_" + type, Rarity.UNCOMMON, List.of(
                                new EntityAttributeModifier(gem.getAttribute(),gem.getValue() * material.getBonus(), gem.getOperation()),
                                material.getModifier(),
                                material.getSecondaryModifier())
                        );
                    }

                }
            }
        }
    }

    public static Item CUT_QUARTZ = registerItem("cut_quartz");
    public static Item CUT_DIAMOND = registerItem("cut_diamond");
    public static Item CUT_EMERALD = registerItem("cut_emerald");
    public static Item CUT_LAPIS_LAZULI = registerItem("cut_lapis_lazuli");
    public static Item CUT_AMETHYST = registerItem("cut_amethyst");
    public static Item CUT_REDSTONE = registerItem("cut_redstone");
    public static Item CUT_RESIN = registerItem("cut_resin");
    public static Item CUT_GLOWSTONE = registerItem("cut_glowstone");
    public static Item CUT_GHAST_TEAR = registerItem("cut_ghast_tear");

    public static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        var key = RegistryKey.of(RegistryKeys.ITEM, Helper.id(name));
        Item item = factory.apply(settings.registryKey(key));
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, key, item);
    }

    public static Item registerItem(String name) {
        return registerItem(name, SimplePolymerItem::new, new Item.Settings());
    }

    public static void callItems() {

        ItemGroup.Builder builder = PolymerItemGroupUtils.builder();
        builder.icon(() -> new ItemStack(Helper.getItem("gold_ring")));
        builder.displayName(Text.translatable("itemGroup.polydoodads"));

        builder.entries(((displayContext, entries) -> {
            for (Item doodad : Doodad.DOODADS) { entries.add(doodad); }
            for (Item item : ModItems.ITEMS) { entries.add(item); }
        }));

        ItemGroup polymerGroup = builder.build();
        PolymerItemGroupUtils.registerPolymerItemGroup(Helper.id("polydoodads"), polymerGroup);

    }
}
