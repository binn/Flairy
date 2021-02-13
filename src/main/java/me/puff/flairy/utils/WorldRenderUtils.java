package me.puff.flairy.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class WorldRenderUtils {

    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void drawText(String str, double x, double y, double z, double scale, int color) {
        glSetup(x, y, z);

        GL11.glScaled(-0.025 * scale, -0.025 * scale, 0.025 * scale);

        int i = mc.textRenderer.getWidth(str) / 2;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, VertexFormats.POSITION_COLOR);
        float f = mc.options.getTextBackgroundOpacity(0.25F);
        bufferbuilder.vertex(-i - 1, -1, 0.0D).color(0.0F, 0.0F, 0.0F, f).next();
        bufferbuilder.vertex(-i - 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, f).next();
        bufferbuilder.vertex(i + 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, f).next();
        bufferbuilder.vertex(i + 1, -1, 0.0D).color(0.0F, 0.0F, 0.0F, f).next();
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        mc.textRenderer.draw(new MatrixStack(), str, -i, 0, 553648127);
        mc.textRenderer.draw(new MatrixStack(), str, -i, 0, color);

        glCleanup();
    }

    public static void glSetup(double x, double y, double z) {
        GL11.glPushMatrix();
        RenderUtils.offsetRender();
        GL11.glTranslated(x, y, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-mc.player.yaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(mc.player.pitch, 1.0F, 0.0F, 0.0F);
        GL11.glDepthFunc(GL11.GL_ALWAYS);

        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

    }

    public static void glCleanup() {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

}