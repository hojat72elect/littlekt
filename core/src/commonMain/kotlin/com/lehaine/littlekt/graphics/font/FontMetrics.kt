package com.lehaine.littlekt.graphics.font

/**
 * @author Colton Daily
 * @date 1/5/2022
 */
data class FontMetrics(
    /**
     * The size of the font
     */
    val size: Float = 0f,
    /**
     * The max top for any character such as `É`
     */
    val top: Float = 0f,
    /**
     * The ascent
     */
    val ascent: Float = 0f,
    /**
     * The base line
     */
    val baseline: Float = 0f,
    /**
     * The descent
     */
    val descent: Float = 0f,
    /**
     * The descent + line gap
     */
    val bottom: Float = 0f,
    /**
     * Extra height
     */
    val leading: Float = 0f,
    /**
     * The max width
     */
    val maxWidth: Float = 0f,
    val capHeight:Float = 0f
) {
    /**
     * 'E' height
     */
    val emHeight get() = ascent - descent

    /**
     *'É' + 'j' + line gap
     */
    val lineHeight get() = top - bottom
}