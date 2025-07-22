package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

        registerItem("cut_quartz");
        registerItem("cut_diamond");
        registerItem("cut_emerald");
        registerItem("cut_lapis_lazuli");
        registerItem("cut_amethyst");
        registerItem("cut_redstone");
        registerItem("cut_resin");
        registerItem("cut_glowstone");
        registerItem("cut_ghast_tear");

    }

    public static void registerItem(String name, Item.Settings settings) {

        Item item = new AutoPolymerItem(settings, name);
        Registry.register(Registries.ITEM, Helper.id(name), item);
        ITEMS.add(item);

    }

    public static void registerItem(String name) {
        registerItem(name, new Item.Settings());
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
