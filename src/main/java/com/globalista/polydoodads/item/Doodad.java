package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.PolyDoodads;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Doodad extends TrinketItem implements PolymerItem {

    public static ArrayList<Item> DOODADS = new ArrayList<>();

    private String name;
    private Rarity rarity;
    private AttributeModifiersComponent customAttributes = AttributeModifiersComponent.builder().build();

    public Doodad(Settings settings) {
        super(settings.maxCount(1));
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        for (var entry : this.customAttributes.modifiers()) {
            modifiers.put(entry.attribute(),
                    new EntityAttributeModifier(slotIdentifier, entry.modifier().value(), entry.modifier().operation()));
        }
        return modifiers;
    }

    public static void create(String name, Rarity rarity, List<EntityAttributeModifier> modifiers) {

        AttributeModifiersComponent.Builder attributes = AttributeModifiersComponent.builder();

        for (EntityAttributeModifier modifier : modifiers) {
            var attribute = Registries.ATTRIBUTE.getEntry(modifier.id());
            attributes.add(attribute.get(),
                    new EntityAttributeModifier(Identifier.of(PolyDoodads.MOD_ID, "modifier"), modifier.value(), modifier.operation()),
                    AttributeModifierSlot.ANY);
        }

        boolean fireproof = name.contains("netherite");
        Settings settings = fireproof ? new Settings().fireproof() : new Settings();

        register(name, Doodad::new, settings.rarity(rarity).maxCount(1).attributeModifiers(attributes.build()));
    }

    public static void create(String name, List<EntityAttributeModifier> modifiers) {
        create(name, Rarity.COMMON, modifiers);
    }

    private static Item register(String name, Function<Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Helper.id(name));

        Item item = factory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        DOODADS.add(item);

        return item;
    }


    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.GOLD_NUGGET;
    }

    public static void callDoodads(){}
}

