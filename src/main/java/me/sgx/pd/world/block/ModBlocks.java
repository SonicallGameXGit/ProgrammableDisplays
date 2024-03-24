package me.sgx.pd.world.block;

import me.sgx.pd.ProgrammableDisplays;
import me.sgx.pd.world.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ModBlocks {
	public static final Block DISPLAY_BLOCK = build(
			"display",
			new DisplayBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK)),
			new FabricItemSettings()
	);

	public static void initialize() {}

	public static Block build(@NotNull String id, @NotNull Block block, FabricItemSettings item) {
		Block registeredBlock = Registry.register(
				Registries.BLOCK,
				new Identifier(ProgrammableDisplays.MOD_ID, id),
				block
		);

		if(item != null) ModItems.build(id, new BlockItem(registeredBlock, item));
		return registeredBlock;
	}
}