package me.sgx.pd.world.block.entity.renderer;

import me.sgx.pd.world.block.entity.DisplayBlockEntity;
import me.sgx.pd.world.block.entity.ModBlockEntityTypes;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class ModBlockEntityRenderer {
	public static BlockEntityRendererFactory<DisplayBlockEntity> DISPLAY_BLOCK_ENTITY_RENDERER = build(
			ModBlockEntityTypes.DISPLAY_BLOCK_ENTITY_TYPE,
			DisplayBlockEntityRenderer::new
	);

	public static void initialize() {}

	public static <T extends BlockEntity> BlockEntityRendererFactory<T> build(BlockEntityType<T> blockEntity, BlockEntityRendererFactory<T> blockEntityRenderer) {
		BlockEntityRendererFactories.register(blockEntity, blockEntityRenderer);
		return blockEntityRenderer;
	}
}