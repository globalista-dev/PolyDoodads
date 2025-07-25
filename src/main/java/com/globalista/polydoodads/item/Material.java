package com.globalista.polydoodads.item;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.UUID;

public class Material {

    private String name;
    private Item item;
    private float bonus;

    private String modifierAttributeId;
    private EntityAttributeModifier modifier;
    private EntityAttribute attribute1;
    private EntityAttribute attribute2;

    private String secondaryModifierAttributeId;
    private EntityAttributeModifier secondaryModifier;

    public static ArrayList<Material> MATERIALS = new ArrayList<>();

    public Material(String name, Item item, float bonus,
                    String modifierAttributeId, double modifierValue, EntityAttributeModifier.Operation modifierOperation,
                    String secondaryModifierAttributeId, Double secondaryModifierValue, EntityAttributeModifier.Operation secondaryModifierOperation,
                    EntityAttribute attribute1, EntityAttribute attribute2) {
        this.name = name;
        this.item = item;
        this.bonus = bonus;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;

        this.modifierAttributeId = modifierAttributeId;
        this.modifier = new EntityAttributeModifier(modifierAttributeId, modifierValue, modifierOperation);
        if(secondaryModifierAttributeId != null){
            this.secondaryModifier = new EntityAttributeModifier(secondaryModifierAttributeId, secondaryModifierValue, secondaryModifierOperation);
        }
    }

    public static Material COPPER = new Material(
            "copper", Items.COPPER_INGOT, 1f,
            "generic.armor", 1f, EntityAttributeModifier.Operation.ADDITION,
            null, null, null,
            EntityAttributes.GENERIC_ARMOR, null
    );

    public static Material IRON = new Material(
            "iron", Items.IRON_INGOT, 1.5f,
            "generic.armor", 2.0, EntityAttributeModifier.Operation.ADDITION,
            "generic.knockback_resistance", 0.05, EntityAttributeModifier.Operation.MULTIPLY_BASE,
            EntityAttributes.GENERIC_ARMOR, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE
    );

    public static Material GOLD = new Material(
            "gold", Items.GOLD_INGOT, 2f,
            "generic.armor", 1.0, EntityAttributeModifier.Operation.ADDITION,
            "generic.knockback_resistance", 0.025, EntityAttributeModifier.Operation.MULTIPLY_BASE,
            EntityAttributes.GENERIC_ARMOR, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE
    );

    public static Material NETHERITE = new Material(
            "netherite", Items.NETHERITE_INGOT, 3f,
            "generic.armor", 3.0, EntityAttributeModifier.Operation.ADDITION,
            "generic.knockback_resistance", 0.1, EntityAttributeModifier.Operation.MULTIPLY_BASE,
            EntityAttributes.GENERIC_ARMOR, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE
    );

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

    public String getModifierAttributeId() {
        return modifierAttributeId;
    }

    public EntityAttributeModifier getSecondaryModifier() {
        return secondaryModifier;
    }

    public String getSecondaryAttributeId() {
        return secondaryModifierAttributeId;
    }

    public EntityAttribute getAttribute1() {
        return attribute1;
    }

    public EntityAttribute getAttribute2() {
        return attribute2;
    }

    public float getBonus() {
        return bonus;
    }

    public Item getItem() {
        return item;
    }
}
