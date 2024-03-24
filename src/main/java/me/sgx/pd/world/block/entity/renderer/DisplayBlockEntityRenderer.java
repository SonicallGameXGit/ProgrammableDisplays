package me.sgx.pd.world.block.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import me.sgx.pd.world.block.entity.DisplayBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class DisplayBlockEntityRenderer implements BlockEntityRenderer<DisplayBlockEntity> {
	public DisplayBlockEntityRenderer(BlockEntityRendererFactory.Context context) {}

	@Override
	public void render(DisplayBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		entity.update();

		matrices.push();
		matrices.translate(0.0f, 0.0f, 1.0f);
		matrices.scale(1.0f, (float) DisplayBlockEntity.HEIGHT / DisplayBlockEntity.WIDTH, 1.0f);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();

		RenderSystem.disableCull();
		RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
		RenderSystem._setShaderTexture(0, entity.texture);
		RenderSystem.enableDepthTest();

		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
		bufferBuilder.vertex(matrices.peek().getPositionMatrix(), 0.0f, 0.0f, 0.0f).texture(0.0f, 0.0f).color(255, 255, 255, 255).next();
		bufferBuilder.vertex(matrices.peek().getPositionMatrix(), 1.0f, 0.0f, 0.0f).texture(1.0f, 0.0f).color(255, 255, 255, 255).next();
		bufferBuilder.vertex(matrices.peek().getPositionMatrix(), 1.0f, 1.0f, 0.0f).texture(1.0f, 1.0f).color(255, 255, 255, 255).next();
		bufferBuilder.vertex(matrices.peek().getPositionMatrix(), 0.0f, 1.0f, 0.0f).texture(0.0f, 1.0f).color(255, 255, 255, 255).next();
		tessellator.draw();

		matrices.pop();
	}
}