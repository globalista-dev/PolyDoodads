package com.globalista.polydoodads.item;

import com.globalista.polydoodads.Helper;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class AutoPolymerItem extends Item implements PolymerItem {

    private final PolymerModelData polymerModelData;
    public AutoPolymerItem(Settings settings, String modelId) {
        super(settings);

        polymerModelData = PolymerResourcePackUtils.requestModel(Items.GOLD_NUGGET, Helper.id(modelId).withPrefixedPath("item/"));

    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.value();
    }

}
