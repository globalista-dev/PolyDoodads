package com.globalista.polydoodads.item;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class Material {

    private String name;
    private Item item;
    private float bonus;
    private EntityAttributeModifier modifier;
    private EntityAttributeModifier secondaryModifier;

    public static ArrayList<Material> MATERIALS = new ArrayList<>();

    public Material(String name, Item item, float bonus, EntityAttributeModifier modifier, EntityAttributeModifier secondaryModifier) {
        this.name = name;
        this.item = item;
        this.bonus = bonus;
        this.modifier = modifier;
        this.secondaryModifier = secondaryModifier;
    }

    public static Material COPPER = new Material("copper", Items.COPPER_INGOT, 1f,
            new EntityAttributeModifier(Identifier.of("armor"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE), null);
    public static Material IRON = new Material("iron", Items.IRON_INGOT, 1.5f,
            new EntityAttributeModifier(Identifier.of("armor"), 1.0f, EntityAttributeModifier.Operation.ADD_VALUE),
            new EntityAttributeModifier(Identifier.of("knockback_resistance"), 0.1f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static Material GOLD = new Material("gold", Items.GOLD_INGOT, 2f,
            new EntityAttributeModifier(Identifier.of("armor"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE),
            new EntityAttributeModifier(Identifier.of("block_break_speed"), 0.25f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static Material NETHERITE = new Material("netherite", Items.NETHERITE_INGOT, 3f,
            new EntityAttributeModifier(Identifier.of("armor"), 2.0f, EntityAttributeModifier.Operation.ADD_VALUE),
            new EntityAttributeModifier(Identifier.of("burning_time"), -0.25f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    static {
        MATERIALS.add(COPPER);
        MATERIALS.add(IRON);
        MATERIALS.add(GOLD);
        MATERIALS.add(NETHERITE);
    }

    public String getName() {
        return name;
    }

    public EntityAttributeModifier getModifier() {
        return modifier;
    }

    public EntityAttributeModifier getSecondaryModifier() {
        return secondaryModifier;
    }

    public float getBonus() {
        return bonus;
    }

    public Item getItem() {
        return item;
    }
}
