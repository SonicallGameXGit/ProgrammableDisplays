package me.sgx.pd.world;

import me.sgx.pd.ProgrammableDisplays;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static void initialize() {}

	public static Item build(String id, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(ProgrammableDisplays.MOD_ID, id), item);
	}
}