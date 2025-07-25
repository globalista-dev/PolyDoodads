package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.PolyDoodads;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Doodad extends TrinketItem implements PolymerItem {

    public static final ArrayList<Item> DOODADS = new ArrayList<>();

    private final String name;
    private final PolymerModelData polymerModelData;
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    private List<AttributeEntry> configurableModifiers = List.of();

    public Doodad(Settings settings, String name, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
        super(settings);
        this.name = name;
        this.polymerModelData = PolymerResourcePackUtils.requestModel(
                Items.GOLD_NUGGET,
                Helper.id(name).withPrefixedPath("item/")
        );
        this.attributeModifiers = attributeModifiers;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        for (var modifier : this.configurableModifiers) {
            modifiers.put(modifier.attribute,
                    new EntityAttributeModifier(modifier.attributeId, modifier.value, modifier.operation));
        }
        return modifiers;
    }

    public record Modifier(EntityAttribute attribute, String name, float value, EntityAttributeModifier.Operation operation) { }

    public void setModifiers(List<AttributeEntry> modifiers){
        this.configurableModifiers = modifiers;
    }

    public static void create(String name, Rarity rarity, List<AttributeEntry> entries) {


        Settings settings = name.contains("netherite") ? new Settings().fireproof() : new Settings();
        register(name, settings.rarity(rarity).maxCount(1), entries);
    }

    public static void create(String name, List<AttributeEntry> entries) {
        create(name, Rarity.COMMON, entries);
    }

    private static void register(String name, Settings settings, List<AttributeEntry> entries) {

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

        for (AttributeEntry entry : entries) {
            EntityAttribute attribute = Registries.ATTRIBUTE.get(new Identifier(entry.attributeId()));
            if (attribute == null) {
                PolyDoodads.LOGGER.warn("Unknown attribute: " + entry.attributeId());
                continue;
            }
            builder.put(attribute, new EntityAttributeModifier(
                    "modifier_" + name, // Use a string ID here
                    entry.value(),
                    entry.operation()
            ));
        }


        Doodad item = new Doodad(settings, name, builder.build());
        item.setModifiers(entries);
        Registry.register(Registries.ITEM, Helper.id(name), item);
        DOODADS.add(Helper.getItem(name));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return polymerModelData.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return polymerModelData.value();
    }

    public static void callDoodads() {}

    public static class AttributeEntry {
        private final EntityAttribute attribute;
        private final String attributeId;
        private final double value;
        private final EntityAttributeModifier.Operation operation;

        public AttributeEntry(EntityAttribute attribute, String attributeId, double value, EntityAttributeModifier.Operation operation) {
            this.attribute = attribute;
            this.attributeId = attributeId;
            this.value = value;
            this.operation = operation;
        }

        public EntityAttribute getAttribute() {
            return attribute;
        }

        public String attributeId() {
            return attributeId;
        }

        public double value() {
            return value;
        }

        public EntityAttributeModifier.Operation operation() {
            return operation;
        }
    }
}
