package me.sgx.pd.world.block.entity;

import me.sgx.pd.ProgrammableDisplays;
import me.sgx.pd.world.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
	public static final BlockEntityType<DisplayBlockEntity> DISPLAY_BLOCK_ENTITY_TYPE = build(
			"display",
			FabricBlockEntityTypeBuilder.create(DisplayBlockEntity::new, ModBlocks.DISPLAY_BLOCK).build()
	);

	public static void initialize() {}

	private static <T extends BlockEntity> BlockEntityType<T> build(String id, BlockEntityType<T> blockEntityType) {
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ProgrammableDisplays.MOD_ID, id), blockEntityType);
	}
}