package com.globalista.polydoodads;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class Helper {

    public static Identifier id(String name) {
        return new Identifier(PolyDoodads.MOD_ID, name);
    }

    public static Item getItem(String name, boolean vanilla) {
        return vanilla ? Registries.ITEM.get(new Identifier("minecraft", name)) : Registries.ITEM.get(id(name));
    }

    public static Item getItem(String name) {
        return getItem(name, false);
    }

    public static String format(String id) {
        String withoutNamespace = id.substring(id.indexOf(':') + 1);
        String[] parts = withoutNamespace.split("_");
        StringBuilder formattedName = new StringBuilder();

        for (String part : parts) {
            if (part.equalsIgnoreCase("of") || part.equalsIgnoreCase("the")) {
                formattedName.append(part.toLowerCase());
            } else {
                formattedName.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase());
            }
            formattedName.append(" ");
        }

        return formattedName.toString().trim();
    }


}
