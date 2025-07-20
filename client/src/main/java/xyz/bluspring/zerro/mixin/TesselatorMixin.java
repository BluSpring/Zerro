package xyz.bluspring.zerro.mixin;

import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

@Mixin(Tessellator.class)
public abstract class TesselatorMixin {
    /*@Shadow private boolean isDrawing;
    @Shadow private int vertexCount;
    @Shadow private int rawBufferIndex;
    @Shadow protected abstract void reset();
    @Shadow private IntBuffer intBuffer;
    @Shadow private int[] rawBuffer;
    @Shadow private ByteBuffer byteBuffer;
    @Shadow private int vboIndex;
    @Shadow private int vboCount;
    @Shadow private IntBuffer vertexBuffers;
    @Shadow private boolean hasTexture;
    @Shadow private FloatBuffer floatBuffer;
    @Shadow private boolean hasBrightness;
    @Shadow private ShortBuffer shortBuffer;
    @Shadow private boolean hasNormals;
    @Shadow private boolean hasColor;
    @Shadow private int drawMode;
    @Shadow private static boolean convertQuadsToTriangles;

    /**
     * @author BluSpring
     * @reason Try to optimize world rendering
     *//*
    @Overwrite
    public int draw() {
        if (!this.isDrawing)
            throw new IllegalStateException("Not tesselating!");

        this.isDrawing = false;

        if (this.vertexCount > 0) {
            this.intBuffer.clear();
            this.intBuffer.put(this.rawBuffer, 0, this.rawBufferIndex);

            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.rawBufferIndex * 4);

            this.vboIndex = (this.vboIndex + 1) % this.vboCount;
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vertexBuffers.get(this.vboIndex));
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.byteBuffer, GL15.GL_STREAM_DRAW);

            if (this.hasTexture) {
                GL15.glBindBuffer(GL31.GL_TEXTURE_BUFFER, );
                if (zerro$supportsVBOs) {
                    GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 32, 12L);
                } else {
                    this.floatBuffer.position(3);
                    GL11.glTexCoordPointer(2, 32, this.floatBuffer);
                }

                GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            }

            if (this.hasBrightness) {
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapEnabled);
                if (zerro$supportsVBOs) {
                    GL11.glTexCoordPointer(2, GL11.GL_SHORT, 32, 28L);
                } else {
                    this.shortBuffer.position(14);
                    GL11.glTexCoordPointer(2, 32, this.shortBuffer);
                }

                GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapDisabled);
            }

            if (this.hasColor) {
                if (zerro$supportsVBOs) {
                    GL11.glColorPointer(4, 5121, 32, 20L);
                } else {
                    this.byteBuffer.position(20);
                    GL11.glColorPointer(4, true, 32, this.byteBuffer);
                }

                GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
            }

            if (this.hasNormals) {
                if (zerro$supportsVBOs) {
                    GL11.glNormalPointer(GL11.GL_UNSIGNED_BYTE, 32, 24L);
                } else {
                    this.byteBuffer.position(24);
                    GL11.glNormalPointer(32, this.byteBuffer);
                }

                GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
            }

            if (zerro$supportsVBOs) {
                GL11.glVertexPointer(3, GL11.GL_FLOAT, 32, 0L);
            } else {
                this.floatBuffer.position(0);
                GL11.glVertexPointer(3, 32, this.floatBuffer);
            }

            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            if (this.drawMode == 7 && convertQuadsToTriangles) {
                GL30.glBindVertexArray(GL30.GL_VERTEX_ARRAY_BINDING);
                GL32.glDrawElementsBaseVertex(4, vertexBuffers, 0);
                //GL11.glDrawArrays(4, 0, this.vertexCount);
            } else {
                GL32.glDrawElementsBaseVertex(this.drawMode, vertexBuffers, 0);
            }

            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
            if (this.hasTexture) {
                GL11.glDisableClientState(32888);
            }

            if (this.hasBrightness) {
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapEnabled);
                GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapDisabled);
            }

            if (this.hasColor) {
                GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
            }

            if (this.hasNormals) {
                GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
            }
        }

        int index = this.rawBufferIndex * 4;
        this.reset();
        return index;
    }*/
}
