package com.globalista.polydoodads.item;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.minecraft.entity.attribute.EntityAttributes.*;

public class Gem {

    private String name;
    private Item item;
    private EntityAttribute attribute;
    private EntityAttributeModifier.Operation operation;
    private float value;
    private String attributename;

    public static ArrayList<Gem> GEMS = new ArrayList<>();

    public Gem(String name, Item item, EntityAttribute attribute, EntityAttributeModifier.Operation operation, float value, String attributename) {
        this.name = name;
        this.item = item;
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
        this.attributename = attributename;
    }

    public static Gem QUARTZ = new Gem("quartz", Items.QUARTZ, GENERIC_MOVEMENT_SPEED,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.05f, "generic.movement_speed");

    public static Gem DIAMOND = new Gem("diamond", Items.DIAMOND,GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.05f, "generic.attack_damage");

    public static Gem EMERALD = new Gem("emerald", Items.EMERALD, GENERIC_LUCK,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.125f, "generic.luck");

    public static Gem LAPIS_LAZULI = new Gem("lapis_lazuli", Items.LAPIS_LAZULI, GENERIC_ATTACK_KNOCKBACK,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.125f, "generic.attack_knockback");

    public static Gem AMETHYST = new Gem("amethyst", Items.AMETHYST_SHARD, GENERIC_ARMOR_TOUGHNESS,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.05f, "generic.armor_toughness");

    public static Gem REDSTONE = new Gem("redstone", Items.REDSTONE, GENERIC_MAX_HEALTH,
            EntityAttributeModifier.Operation.ADDITION, 2.0f, "generic.max_health");

    public static Gem GLOWSTONE = new Gem("glowstone", Items.GLOWSTONE, GENERIC_ATTACK_SPEED,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.05f, "generic.attack_speed");

    public static Gem GHAST_TEAR = new Gem ("ghast_tear", Items.GHAST_TEAR, GENERIC_KNOCKBACK_RESISTANCE,
            EntityAttributeModifier.Operation.MULTIPLY_BASE, 0.1f, "generic.knockback_resistance");

    static {
        GEMS.add(QUARTZ);
        GEMS.add(DIAMOND);
        GEMS.add(EMERALD);
        GEMS.add(LAPIS_LAZULI);
        GEMS.add(AMETHYST);
        GEMS.add(REDSTONE);
        GEMS.add(GLOWSTONE);
        GEMS.add(GHAST_TEAR);
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public EntityAttribute getAttribute() {
        return attribute;
    }

    public EntityAttributeModifier.Operation getOperation() {
        return operation;
    }

    public String getAttributename() {
        return attributename;
    }
}
