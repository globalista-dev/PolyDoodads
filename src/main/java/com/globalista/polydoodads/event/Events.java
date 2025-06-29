package com.globalista.polydoodads.event;

import com.globalista.polydoodads.Helper;
import com.google.common.collect.HashBiMap;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Events {

    private static HashMap<Item, Item> CUTMAP = new HashMap<>();
    static {
        CUTMAP.put(Items.DIAMOND, Helper.getItem("cut_diamond"));
        CUTMAP.put(Items.EMERALD, Helper.getItem("cut_emerald"));
        CUTMAP.put(Items.QUARTZ, Helper.getItem("cut_quartz"));
        CUTMAP.put(Items.LAPIS_LAZULI, Helper.getItem("cut_lapis_lazuli"));
        CUTMAP.put(Items.AMETHYST_SHARD, Helper.getItem("cut_amethyst"));
        CUTMAP.put(Items.REDSTONE_BLOCK, Helper.getItem("cut_redstone"));
        CUTMAP.put(Items.GLOWSTONE, Helper.getItem("cut_glowstone"));
        CUTMAP.put(Items.GHAST_TEAR, Helper.getItem("cut_ghast_tear"));
        CUTMAP.put(Items.RESIN_CLUMP, Helper.getItem("cut_resin"));
    }

    static {
        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {

            BlockState state = world.getBlockState(hitResult.getBlockPos());
            ItemStack itemStack = player.getStackInHand(hand);

            if(!player.isSpectator() && world instanceof ServerWorld && state.isOf(Blocks.GRINDSTONE) && CUTMAP.containsKey(itemStack.getItem())) {

                player.giveItemStack(new ItemStack(CUTMAP.get(itemStack.getItem()), 1));
                world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.PLAYERS, 0.5f, 1f);
                ExperienceOrbEntity.spawn((ServerWorld) world, hitResult.getPos(), 1);
                itemStack.decrement(1);
                return ActionResult.SUCCESS_SERVER;

            }

            return ActionResult.PASS;
        }));
    }

    public static void callEvents() {
    }


}
