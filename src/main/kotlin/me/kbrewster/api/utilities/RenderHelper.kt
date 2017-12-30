package me.kbrewster.api.utilities

import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import java.awt.Color


inline fun matrix(func: () -> Unit) {
    func.invoke()
}

/**
 * Draws a thin horizontal line between two points.
 */
fun drawHorizontalLine(startX: Int, endX: Int, y: Int, color: Int) {
    var startX = startX
    var endX = endX
    if (endX < startX) {
        val i = startX
        startX = endX
        endX = i
    }

    drawRect(startX, y, endX + 1, y + 1, color)
}

/**
 * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
 */
fun drawVerticalLine(x: Int, startY: Int, endY: Int, color: Int) {
    var startY = startY
    var endY = endY
    if (endY < startY) {
        val i = startY
        startY = endY
        endY = i
    }

    drawRect(x, startY + 1, x + 1, endY, color)
}

/**
 * Draws a solid color rectangle with the specified coordinates and color.
 */
fun drawRect(left: Int, top: Int, right: Int, bottom: Int, color: Int) {
    var left = left
    var top = top
    var right = right
    var bottom = bottom
    if (left < right) {
        val i = left
        left = right
        right = i
    }

    if (top < bottom) {
        val j = top
        top = bottom
        bottom = j
    }

    val f3 = (color shr 24 and 255).toFloat() / 255.0f
    val f = (color shr 16 and 255).toFloat() / 255.0f
    val f1 = (color shr 8 and 255).toFloat() / 255.0f
    val f2 = (color and 255).toFloat() / 255.0f
    val tessellator = Tessellator.getInstance()
    val bufferbuilder = tessellator.buffer
    GlStateManager.enableBlend()
    GlStateManager.disableTexture2D()
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO)
    GlStateManager.color(f, f1, f2, f3)
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION)
    bufferbuilder.pos(left.toDouble(), bottom.toDouble(), 0.0).endVertex()
    bufferbuilder.pos(right.toDouble(), bottom.toDouble(), 0.0).endVertex()
    bufferbuilder.pos(right.toDouble(), top.toDouble(), 0.0).endVertex()
    bufferbuilder.pos(left.toDouble(), top.toDouble(), 0.0).endVertex()
    tessellator.draw()
    GlStateManager.enableTexture2D()
    GlStateManager.disableBlend()
}

/**
 * Renders the specified text to the screen, center-aligned. Args : renderer, string, x, y, color
 */
fun drawCenteredString(fontRendererIn: FontRenderer, text: String, x: Int, y: Int, color: Int) {
    fontRendererIn.drawStringWithShadow(text, (x - fontRendererIn.getStringWidth(text) / 2).toFloat(), y.toFloat(), color)
}

/**
 * Renders the specified text to the screen. Args : renderer, string, x, y, color
 */
fun drawString(fontRendererIn: FontRenderer, text: String, x: Int, y: Int, color: Int) {
    fontRendererIn.drawStringWithShadow(text, x.toFloat(), y.toFloat(), color)
}

/**
 * Draws a textured rectangle at z = 0. Args: x, y, u, v, width, height, textureWidth, textureHeight
 */
fun drawModalRectWithCustomSizedTexture(x: Int, y: Int, u: Float, v: Float, width: Int, height: Int, textureWidth: Float, textureHeight: Float) {
    val f = 1.0f / textureWidth
    val f1 = 1.0f / textureHeight
    val tessellator = Tessellator.getInstance()
    val bufferbuilder = tessellator.buffer
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX)
    bufferbuilder.pos(x.toDouble(), (y + height).toDouble(), 0.0).tex((u * f).toDouble(), ((v + height.toFloat()) * f1).toDouble()).endVertex()
    bufferbuilder.pos((x + width).toDouble(), (y + height).toDouble(), 0.0).tex(((u + width.toFloat()) * f).toDouble(), ((v + height.toFloat()) * f1).toDouble()).endVertex()
    bufferbuilder.pos((x + width).toDouble(), y.toDouble(), 0.0).tex(((u + width.toFloat()) * f).toDouble(), (v * f1).toDouble()).endVertex()
    bufferbuilder.pos(x.toDouble(), y.toDouble(), 0.0).tex((u * f).toDouble(), (v * f1).toDouble()).endVertex()
    tessellator.draw()
}

/**
 * Draws a scaled, textured, tiled modal rect at z = 0. This method isn't used anywhere in vanilla code.
 */
fun drawScaledCustomSizeModalRect(x: Int, y: Int, u: Float, v: Float, uWidth: Int, vHeight: Int, width: Int, height: Int, tileWidth: Float, tileHeight: Float) {
    val f = 1.0f / tileWidth
    val f1 = 1.0f / tileHeight
    val tessellates = Tessellator.getInstance()
    val bufferBuilder = tessellates.buffer
    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX)
    bufferBuilder.pos(x.toDouble(), (y + height).toDouble(), 0.0).tex((u * f).toDouble(), ((v + vHeight.toFloat()) * f1).toDouble()).endVertex()
    bufferBuilder.pos((x + width).toDouble(), (y + height).toDouble(), 0.0).tex(((u + uWidth.toFloat()) * f).toDouble(), ((v + vHeight.toFloat()) * f1).toDouble()).endVertex()
    bufferBuilder.pos((x + width).toDouble(), y.toDouble(), 0.0).tex(((u + uWidth.toFloat()) * f).toDouble(), (v * f1).toDouble()).endVertex()
    bufferBuilder.pos(x.toDouble(), y.toDouble(), 0.0).tex((u * f).toDouble(), (v * f1).toDouble()).endVertex()
    tessellates.draw()
}

fun colour(red: Int, green: Int, blue: Int, alpha: Int = 0) =
        Color(red, green, blue, alpha).rgb

fun drawPolygon(color: Int, segments: Int, x: Float, y: Float, r: Float) {
    val theta = 2 * Math.PI / segments
    val cos = Math.cos(theta)
    val sin = Math.sin(theta)

    var xHolder: Double
    var unitCircleX = 1.0
    var unitCircleY = 0.0

    val alpha = (color shr 24 and 255).toFloat() / 255.0f
    val red = (color shr 16 and 255).toFloat() / 255.0f
    val green = (color shr 8 and 255).toFloat() / 255.0f
    val blue = (color and 255).toFloat() / 255.0f

    GlStateManager.pushMatrix()
    GlStateManager.color(red, green, blue, alpha)

    GL11.glBegin(GL11.GL_LINE_LOOP)
    for (i in 0 until segments) {
        GL11.glVertex2d(unitCircleX * r + x, unitCircleY * r + y)
        xHolder = unitCircleX
        unitCircleX = cos * unitCircleX - sin * unitCircleY
        unitCircleY = sin * xHolder + cos * unitCircleY
    }
    GL11.glEnd()

    GlStateManager.popMatrix()
}
