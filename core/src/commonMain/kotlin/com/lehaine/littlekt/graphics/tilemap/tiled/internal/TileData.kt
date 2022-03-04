package com.lehaine.littlekt.graphics.tilemap.tiled.internal

import com.lehaine.littlekt.math.geom.Angle

/**
 * @author Colton Daily
 * @date 3/2/2022
 */
internal data class TileData(
    var id: Int = 0,
    var flipX: Boolean = false,
    var flipY: Boolean = false,
    var rotation: Angle = Angle.ZERO
)
