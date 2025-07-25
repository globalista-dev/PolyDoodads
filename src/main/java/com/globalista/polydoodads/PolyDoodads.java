package com.globalista.polydoodads;

import com.globalista.polydoodads.event.Events;
import com.globalista.polydoodads.item.Doodad;
import com.globalista.polydoodads.item.ModItems;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolyDoodads implements ModInitializer {
	public static final String MOD_ID = "polydoodads";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		Doodad.callDoodads();
		ModItems.init();
		ModItems.callItems();
		Events.callEvents();

		PolymerResourcePackUtils.addModAssets(MOD_ID);
		PolymerResourcePackUtils.markAsRequired();

		LOGGER.info("Hello Fabric world!");

	}
}