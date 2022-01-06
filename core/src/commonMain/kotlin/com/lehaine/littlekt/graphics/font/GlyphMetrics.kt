package com.lehaine.littlekt.graphics.font

import com.lehaine.littlekt.math.Rect

/**
 * @author Colton Daily
 * @date 1/5/2022
 */
data class GlyphMetrics(
    val size: Float = 0f,
    val code: Int = 0,
    val bounds: Rect = Rect(),
    val xAdvance: Float = 0f
) {
    val left: Float get() = bounds.x
    val right: Float get() = bounds.x2
    val top: Float get() = bounds.y
    val bottom: Float get() = bounds.y2
    val width: Float get() = bounds.width
    val height: Float get() = bounds.height
}