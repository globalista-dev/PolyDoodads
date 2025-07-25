package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.PolyDoodads;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final ArrayList<String> TYPES = new ArrayList<>();
    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    static {
        TYPES.add("ring");
        TYPES.add("necklace");
        TYPES.add("anklet");
        TYPES.add("circlet");
        TYPES.add("mask");
    }

    public static final RegistryKey<ItemGroup> POLYMER_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(PolyDoodads.MOD_ID, "polydoodads"));

    // Helper to convert EntityAttributeModifier to Doodad.AttributeEntry
    private static Doodad.AttributeEntry toEntry(EntityAttribute attribute, String attributeId, double value, EntityAttributeModifier.Operation operation) {
        return new Doodad.AttributeEntry(attribute, attributeId, value, operation);
    }

    public static void init() {
        for (Material material : Material.MATERIALS) {
            for (String type : TYPES) {
                List<Doodad.AttributeEntry> baseEntries = new ArrayList<>();

                var mod = material.getModifier();
                baseEntries.add(toEntry(
                        material.getAttribute1(),
                        mod.getName(), // assuming getName() returns String like "generic.armor"
                        mod.getValue(),
                        mod.getOperation()
                ));

                if (material.getSecondaryModifier() != null) {
                    var secMod = material.getSecondaryModifier();
                    baseEntries.add(toEntry(
                            material.getAttribute2(),
                            secMod.getName(),
                            secMod.getValue(),
                            secMod.getOperation()
                    ));
                }

                Doodad.create(material.getName() + "_" + type, baseEntries);

                for (Gem gem : Gem.GEMS) {
                    List<Doodad.AttributeEntry> gemEntries = new ArrayList<>();

                    // Gem attribute ID and scaled value
                    String gemAttrId = gem.getAttributename();
                    double scaledValue = gem.getValue() * material.getBonus();

                    gemEntries.add(toEntry(gem.getAttribute(), gemAttrId, scaledValue, gem.getOperation()));
                    gemEntries.addAll(baseEntries);

                    Rarity rarity;
                    if (material.getSecondaryModifier() == null) {
                        rarity = Rarity.COMMON;
                    } else if (material.getName().contains("netherite")) {
                        rarity = Rarity.RARE;
                    } else {
                        rarity = Rarity.UNCOMMON;
                    }

                    Doodad.create(gem.getName() + "_" + material.getName() + "_" + type, rarity, gemEntries);
                }
            }
        }

        // Simple items registration
        registerItem("cut_quartz");
        registerItem("cut_diamond");
        registerItem("cut_emerald");
        registerItem("cut_lapis_lazuli");
        registerItem("cut_amethyst");
        registerItem("cut_redstone");
        registerItem("cut_glowstone");
        registerItem("cut_ghast_tear");
    }

    public static void registerItem(String name) {
        registerItem(name, new Item.Settings());
    }

    public static void registerItem(String name, Item.Settings settings) {
        Item item = new AutoPolymerItem(settings, name);
        Registry.register(Registries.ITEM, Helper.id(name), item);
        ITEMS.add(Helper.getItem(name));
    }

    public static void callItems() {
        ItemGroup polymerGroup = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Helper.getItem("gold_ring")))
                .displayName(Text.translatable("itemGroup.polydoodads"))
                .entries((displayContext, entries) -> {
                    for (Item doodad : Doodad.DOODADS) {
                        entries.add(Helper.getItem(doodad.toString()));
                    }
                    for (Item item : ITEMS) {
                        entries.add(Helper.getItem(item.toString()));
                    }
                })
                .build();

        PolymerItemGroupUtils.registerPolymerItemGroup(Helper.id("polydoodads"), polymerGroup);
    }
}
