package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import com.globalista.polydoodads.PolyDoodads;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Doodad extends TrinketItem implements PolymerItem {

    public static ArrayList<Item> DOODADS = new ArrayList<>();

    private String name;
    private Rarity rarity;
    private AttributeModifiersComponent component = AttributeModifiersComponent.builder().build();
    private final PolymerModelData polymerModelData;

    public Doodad(Settings settings, String name) {
        super(settings);
        polymerModelData = PolymerResourcePackUtils.requestModel(Items.GOLD_NUGGET, Helper.id(name).withPrefixedPath("item/"));
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        for (var entry : this.component.modifiers()) { modifiers.put(entry.attribute(), new EntityAttributeModifier(slotIdentifier, entry.modifier().value(), entry.modifier().operation())); }
        return modifiers;
    }

    public void setComponent(AttributeModifiersComponent component) { this.component = component; }

    public static void create(String name, Rarity rarity, List<EntityAttributeModifier> modifiers) {

        AttributeModifiersComponent.Builder attributes = AttributeModifiersComponent.builder();

        for (EntityAttributeModifier modifier : modifiers) {
            var attribute = Registries.ATTRIBUTE.getEntry(modifier.id());
            attributes.add(attribute.get(), new EntityAttributeModifier(Identifier.of(PolyDoodads.MOD_ID, "modifier"), modifier.value(), modifier.operation()), AttributeModifierSlot.ANY);
        }

        Settings settings = name.contains("netherite") ? new Settings().fireproof() : new Settings();

        register(name, settings.rarity(rarity).maxCount(1), attributes);
    }

    public static void create(String name, List<EntityAttributeModifier> modifiers) {
        create(name, Rarity.COMMON, modifiers);
    }

    private static void register(String name, Settings settings, AttributeModifiersComponent.Builder attributes) {

        Doodad item = new Doodad(settings, name);
        item.setComponent(attributes.build());

        Registry.register(Registries.ITEM, Helper.id(name), item);

        DOODADS.add(item);

    }


    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.value();
    }

    public static void callDoodads(){}


}

