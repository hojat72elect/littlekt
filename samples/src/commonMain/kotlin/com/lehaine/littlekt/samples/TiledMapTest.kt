package com.lehaine.littlekt.samples

import com.lehaine.littlekt.Context
import com.lehaine.littlekt.ContextListener
import com.lehaine.littlekt.file.vfs.readPixmap
import com.lehaine.littlekt.file.vfs.readTexture
import com.lehaine.littlekt.file.vfs.readTiledMap
import com.lehaine.littlekt.graphics.*
import com.lehaine.littlekt.graphics.gl.ClearBufferMask
import com.lehaine.littlekt.graphics.tilemap.tiled.TiledMap
import com.lehaine.littlekt.input.Key
import com.lehaine.littlekt.util.MutableTextureAtlas
import com.lehaine.littlekt.util.viewport.ExtendViewport

/**
 * @author Colton Daily
 * @date 2/28/2022
 */
class TiledMapTest(context: Context) : ContextListener(context) {

    override suspend fun Context.start() {
        val camera = OrthographicCamera().apply {
            viewport = ExtendViewport(480, 270)
        }

        val batch = SpriteBatch(context, 8191)

        // load the textures manually for the ortho map
        val cavernasTexture =
            resourcesVfs["tiled/Cavernas_by_Adam_Saltsman.png"].readPixmap().addBorderToSlices(context, 8, 8, 2)
        val background = resourcesVfs["ldtk/N2D - SpaceWallpaper1280x448.png"].readTexture()
        val atlas = MutableTextureAtlas(this)
            .add(cavernasTexture.slice(), "Cavernas_by_Adam_Saltsman.png")
            .add(background.slice(), "N2D - SpaceWallpaper1280x448.png")
            .toImmutable()

        // we need to dispose of them if we aren't using them since the atlas generates new textures
        cavernasTexture.dispose()
        background.dispose()

        val maps = mutableListOf<TiledMap>()
        resourcesVfs["tiled/ortho-tiled-world.tmj"].readTiledMap(atlas).also { maps += it }
        resourcesVfs["tiled/iso-tiled-world.tmj"].readTiledMap().also { maps += it }
        resourcesVfs["tiled/staggered-tiled-world.tmj"].readTiledMap().also { maps += it }

        var mapIdx = 0
        onResize { width, height ->
            camera.update(width, height, context)
        }
        onRender { dt ->
            gl.clear(ClearBufferMask.COLOR_BUFFER_BIT)

            if (input.isKeyPressed(Key.W)) {
                camera.position.y -= 10f
            } else if (input.isKeyPressed(Key.S)) {
                camera.position.y += 10f
            }

            if (input.isKeyPressed(Key.D)) {
                camera.position.x += 10f
            } else if (input.isKeyPressed(Key.A)) {
                camera.position.x -= 10f
            }
            camera.update()

            batch.use(camera.viewProjection) {
                maps[mapIdx].render(it, camera, displayObjects = true)
            }

            if (input.isKeyJustPressed(Key.ENTER)) {
                mapIdx++
                if (mapIdx >= maps.size) mapIdx = 0
            }
            if (input.isKeyJustPressed(Key.P)) {
                logger.info { stats }
            }

            if (input.isKeyJustPressed(Key.ESCAPE)) {
                close()
            }
        }
    }
}