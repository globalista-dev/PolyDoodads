package com.globalista.polydoodads.item;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class Gem {

    private String name;
    private Item item;
    private Identifier attribute;
    private EntityAttributeModifier.Operation operation;
    private float value;

    public static ArrayList<Gem> GEMS = new ArrayList<>();

    public Gem(String name, Item item, Identifier attribute, EntityAttributeModifier.Operation operation, float value) {
        this.name = name;
        this.item = item;
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
    }

    public static Gem QUARTZ = new Gem("quartz", Items.QUARTZ, Identifier.of("generic.movement_speed"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05f);

    public static Gem DIAMOND = new Gem("diamond", Items.DIAMOND, Identifier.of("generic.attack_damage"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05f);

    public static Gem EMERALD = new Gem("emerald", Items.EMERALD, Identifier.of("generic.luck"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.125f);

    public static Gem LAPIS_LAZULI = new Gem("lapis_lazuli", Items.LAPIS_LAZULI, Identifier.of("generic.oxygen_bonus"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.125f);

    public static Gem AMETHYST = new Gem("amethyst", Items.AMETHYST_SHARD, Identifier.of("generic.armor_toughness"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05f);

    public static Gem REDSTONE = new Gem("redstone", Items.REDSTONE, Identifier.of("generic.max_health"),
            EntityAttributeModifier.Operation.ADD_VALUE, 2.0f);

    public static Gem GLOWSTONE = new Gem("glowstone", Items.GLOWSTONE, Identifier.of("generic.attack_speed"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05f);

    public static Gem GHAST_TEAR = new Gem ("ghast_tear", Items.GHAST_TEAR, Identifier.of("generic.explosion_knockback_resistance"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.1f);

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

    public Identifier getAttribute() {
        return attribute;
    }

    public EntityAttributeModifier.Operation getOperation() {
        return operation;
    }
}
