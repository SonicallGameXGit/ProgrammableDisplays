package me.sgx.pd;

import me.sgx.pd.world.block.ModBlocks;
import me.sgx.pd.world.ModItems;
import me.sgx.pd.world.block.entity.ModBlockEntityTypes;
import me.sgx.pd.world.block.entity.renderer.ModBlockEntityRenderer;
import net.fabricmc.api.ModInitializer;

public class ProgrammableDisplays implements ModInitializer {
	public static final String MOD_ID = "pd";

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlockEntityTypes.initialize();
		ModBlockEntityRenderer.initialize();
		ModBlocks.initialize();
	}
}
