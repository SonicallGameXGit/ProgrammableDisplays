package me.sgx.pd.world.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import org.python.util.PythonInterpreter;

import java.nio.ByteBuffer;

public class DisplayBlockEntity extends BlockEntity {
	public static final int WIDTH = 128, HEIGHT = 64, CHANNELS = 3;

	public int texture = 0;
	private final ByteBuffer pixels = ByteBuffer.allocateDirect(WIDTH * HEIGHT * CHANNELS);
	private boolean dirty = false;

	private final PythonInterpreter interpreter = new PythonInterpreter();

	public DisplayBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntityTypes.DISPLAY_BLOCK_ENTITY_TYPE, pos, state);

		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels.put((byte) 0);
			pixels.put((byte) 0);
			pixels.put((byte) 0);
		}

		pixels.flip();

		new Thread(() -> {
			interpreter.set("Display", this);
			interpreter.exec(String.join("\n", new String[] {
					"counter = 0",
					"while True:",
					"	if Display.isMousePressed():",
					"		counter += 1",
					"		if counter >= 128:",
					"			Display.setPixel(counter, 32, 0xff00ff)"
			}));
			interpreter.cleanup();
		}).start();
	}

	public boolean isMousePressed() {
		return MinecraftClient.getInstance().mouse.wasLeftButtonClicked();
	}

	public void setPixel(int x, int y, int color) {
		if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) return;

		pixels.put((x + y * WIDTH) * CHANNELS, (byte) ((color >> 16) & 0xFF));
		pixels.put((x + y * WIDTH) * CHANNELS + 1, (byte) ((color >> 8) & 0xFF));
		pixels.put((x + y * WIDTH) * CHANNELS + 2, (byte) (color & 0xFF));

		dirty = true;
	}

	public void update() {
		if(dirty) {
			texture = GL11.glGenTextures();

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, WIDTH, HEIGHT, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, pixels);

			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

			dirty = false;
		}
	}
}