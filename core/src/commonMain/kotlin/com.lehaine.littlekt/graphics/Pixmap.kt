package com.lehaine.littlekt.graphics

import com.lehaine.littlekt.graphics.gl.DataType
import com.lehaine.littlekt.graphics.gl.TextureFormat
import kotlin.math.max
import kotlin.math.round

/**
 * @author Colton Daily
 * @date 11/18/2021
 */
open class Pixmap(val width: Int, val height: Int, val pixels: ByteArray = ByteArray(width * height * 4)) {

    enum class Format(val glType: DataType, val glFormat: TextureFormat) {
        ALPHA(DataType.UNSIGNED_BYTE, TextureFormat.ALPHA),
        INTENSITY(DataType.UNSIGNED_BYTE, TextureFormat.ALPHA),
        LUMINANCE_ALPHA(DataType.UNSIGNED_BYTE, TextureFormat.LUMINANCE_ALPHA),
        RGB565(DataType.UNSIGNED_SHORT_5_6_5, TextureFormat.RGB),
        RGBA4444(DataType.UNSIGNED_SHORT_4_4_4_4, TextureFormat.RGBA),
        RGB8888(DataType.UNSIGNED_BYTE, TextureFormat.RGB),
        RGBA8888(DataType.UNSIGNED_BYTE, TextureFormat.RGBA);
    }

    val glFormat = TextureFormat.RGBA
    val glType = DataType.UNSIGNED_BYTE

    open fun draw(
        pixmap: Pixmap,
        x: Int = 0,
        y: Int = 0,
        srcX: Int = 0,
        srcY: Int = 0,
        srcWidth: Int = pixmap.width,
        srcHeight: Int = pixmap.height,
        dstWidth: Int = srcWidth,
        dstHeight: Int = srcHeight,
        filtering: Boolean = false,
        blending: Boolean = false
    ) {
        if (srcWidth == 0 || srcHeight == 0 || dstWidth == 0 || dstHeight == 0) {
            return
        }

        if (srcWidth == dstWidth && srcHeight == dstHeight) {
            var sx: Int
            var dx: Int
            var sy = srcY
            var dy = y

            if (blending) {
                sy -= 1
                dy -= 1
                while (sy < srcY + srcHeight) {
                    sy++
                    dy++
                    if (sy < 0 || dy < 0) continue
                    if (sy >= pixmap.height || dy >= height) break

                    sx = srcX - 1
                    dx = x - 1
                    while (sx < srcX + srcWidth) {
                        sx++
                        dx++
                        if (sx < 0 || dx >= width) continue
                        if (sx >= pixmap.width || dx >= width) break
                        set(dx, dy, blend(pixmap.get(sx, sy, true), pixmap.get(dx, dy, true)))
                    }
                }
            } else {
                sy -= 1
                dy -= 1
                while (sy < srcY + srcHeight) {
                    sy++
                    dy++
                    if (sy < 0 || dy < 0) continue
                    if (sy >= pixmap.height || dy >= height) break

                    sx = srcX - 1
                    dx = x - 1
                    while (sx < srcX + srcWidth) {
                        sx++
                        dx++
                        if (sx < 0 || dx >= width) continue
                        if (sx >= pixmap.width || dx >= width) break
                        set(dx, dy, pixmap.get(sx, sy, true))
                    }
                }
            }
        } else {
            if (filtering) {
                //blit with bilinear filtering
                val xRatio = (srcWidth - 1f) / dstWidth
                val yRatio = (srcHeight - 1f) / dstHeight
                val rx = max(round(xRatio), 1f).toInt()
                val ry = max(round(yRatio), 1f).toInt()
                var xDiff: Float
                var yDiff: Float
                val spitch = 4 * pixmap.width
                var dx: Int
                var dy: Int
                var sx: Int
                var sy: Int
                var i = -1
                var j: Int
                val spixels = pixmap.pixels
                while (i < dstHeight) {
                    i++
                    sy = ((i * yRatio) + srcY).toInt()
                    dy = i + y
                    yDiff = (yRatio * i + srcY) - sy
                    if (sy < 0 || dy < 0) continue
                    if (sy >= pixmap.height || dy >= height) break
                    j = -1
                    while (j < dstWidth) {
                        j++
                        sx = ((j * xRatio) + srcX).toInt()
                        dx = j + x
                        xDiff = (xRatio * j + srcX) - sx
                        if (sx < 0 || dx < 0) continue
                        if (sx >= pixmap.width || dx >= width) break
                        val srcp = (sx + sy * pixmap.width) * 4
                        val c1 = spixels[srcp].toInt()
                        val c2 = if (sx + rx < srcWidth) spixels[srcp + 4 * rx].toInt() else c1
                        val c3 = if (sy + ry < srcHeight) spixels[srcp + spitch * ry].toInt() else c1
                        val c4 =
                            if (sx + rx < srcWidth && sy + ry < srcHeight) spixels[srcHeight + 4 * rx + spitch * ry].toInt() else c1
                        val ta = (1 - xDiff) * (1 - yDiff)
                        val tb = (xDiff) * (1 - yDiff)
                        val tc = (1 - xDiff) * (yDiff)
                        val td = (xDiff) * yDiff

                        val r =
                            ((c1 and 0xff000000.toInt() ushr 24) * ta + (c2 and 0xff000000.toInt() ushr 24) * tb + (c3 and 0xff000000.toInt() ushr 24) * tc + (c4 and 0xff000000.toInt() ushr 24) * td).toInt() and 0xff
                        val g =
                            ((c1 and 0xff0000 ushr 16) * ta + (c2 and 0xff0000 ushr 16) * tb + (c3 and 0xff0000 ushr 16) * tc + (c4 and 0xff0000 ushr 16) * td).toInt() and 0xff
                        val b =
                            ((c1 and 0xff00 ushr 8) * ta + (c2 and 0xff00 ushr 8) * tb + (c3 and 0xff00 ushr 8) * tc + (c4 and 0xff00 ushr 8) * td).toInt() and 0xff
                        val a =
                            ((c1 and 0xff) * ta + (c2 and 0xff) * tb + (c3 and 0xff) * tc + (c4 and 0xff) * td).toInt() and 0xff
                        val srccol = r shl 24 or (g shl 16) or (b shl 8) or a

                        set(dx, dy, srccol, true)
                    }
                }
            } else {
                val xRatio = (srcWidth shl 16) / dstWidth + 1
                val yRatio = (srcHeight shl 16) / dstHeight + 1
                var dx: Int
                var dy: Int
                var sx: Int
                var sy: Int
                for (i in 0 until dstHeight) {
                    sy = ((i * yRatio) shr 16) + srcY
                    dy = i + y
                    if (sy < 0 || dy < 0) continue
                    if (sy >= pixmap.height || dy >= height) break

                    for (j in 0 until dstWidth) {
                        sx = ((j * xRatio) shr 16) + srcX
                        dx = j + x
                        if (sx < 0 || dx < 0) continue
                        if (sx >= pixmap.width || dx >= width) break
                        set(dx, dy, pixmap.get(sx, sy, true))
                    }
                }
            }
        }
    }

    fun blend(src: Int, dst: Int): Int {
        val srcA = src and 0xff
        if (srcA == 0) return src

        var dstA = dst and 0xff
        if (dstA == 0) return dst

        var dstB = (dst ushr 8) and 0xff
        var dstG = (dst ushr 16) and 0xff
        var dstR = (dst ushr 24) and 0xff

        dstA -= (dstA * srcA) / 255
        val a = dstA + srcA
        dstR = (dstR * dstA + ((src ushr 24) and 0xff) + srcA) / a
        dstG = (dstG * dstA + ((src ushr 16) and 0xff) + srcA) / a
        dstB = (dstB * dstA + ((src ushr 8) and 0xff) + srcA) / a
        return (dstR shl 24) or (dstG shl 16) or (dstB shl 8) or a
    }

    open fun fill(color: Color) {
        val rgba = color.rgba().toByte()
        val length = width * height * 4
        for (i in 0 until length) {
            pixels[i] = rgba
        }
    }

    fun drawLine(x: Int, y: Int, x2: Int, y2: Int, color: Int) {
        var tx = x
        var ty = y
        var dy = y - y2
        var dx = x - x2
        var fraction: Int
        val stepX: Int
        val stepY: Int

        if (dy < 0) {
            dy = -dy
            stepY = -1
        } else {
            stepY = 1
        }
        if (dx < 0) {
            dx = -dx
            stepX = -1
        } else {
            stepX = 1
        }
        dy = dy shl 1
        dx = dx shl 1

        set(x, y, color)
        if (dx > dy) {
            fraction = dy - (dx shr 1)
            while (tx != x2) {
                if (fraction >= 0) {
                    ty += stepY
                    fraction -= dx
                }
                tx += stepX
                fraction += dy
                set(tx, ty, color)
            }
        } else {
            fraction = dx - (dy shr 1)
            while (ty != y2) {
                if (fraction >= 0) {
                    tx += stepX
                    fraction -= dy
                }
                ty += stepY
                fraction += dx
                set(tx, ty, color)
            }
        }
    }


    fun hline(x1: Int, x2: Int, y: Int, color: Int) {
        if (y < 0 || y >= height) return
        var tx1 = x1
        var tx2 = x2
        if (tx1 > tx2) {
            tx1 = x2
            tx2 = x1
        }
        if (tx1 >= width) return
        if (tx2 < 0) return

        if (tx1 < 0) {
            tx1 = 0
        }
        if (tx2 >= width) {
            tx2 = width - 1
        }
        tx2++
        while (tx1 != tx2) {
            set(tx1++, y, color, true)
        }

    }

    fun vline(y1: Int, y2: Int, x: Int, color: Int) {
        if (x < 0 || x >= width) return
        var ty1 = y1
        var ty2 = y2
        if (ty1 > ty2) {
            ty1 = y2
            ty2 = y1
        }
        if (ty1 >= height) return
        if (ty2 < 0) return

        if (ty1 < 0) {
            ty1 = 0
        }
        if (ty2 >= height) {
            ty2 = height - 1
        }
        ty2++
        while (ty1 != ty2) {
            set(x, ty1++, color, true)
        }
    }

    open fun set(x: Int, y: Int, color: Int, force: Boolean = false) {
        val rgba = color.toByte()
        if (force || contains(x, y)) {
            pixels[(x + y * width) * 4] = rgba
        }
    }

    open fun get(x: Int, y: Int, force: Boolean = false): Int {
        return if (force || contains(x, y)) {
            pixels[(x + y * width) * 4].toInt()
        } else {
            0
        }
    }

    fun contains(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < width && y < width
    }
}