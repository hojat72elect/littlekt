package com.lehaine.littlekt.samples

import com.lehaine.littlekt.Context
import com.lehaine.littlekt.ContextListener
import com.lehaine.littlekt.file.vfs.readTexture
import com.lehaine.littlekt.graphics.*
import com.lehaine.littlekt.graphics.gl.ClearBufferMask
import com.lehaine.littlekt.graphics.gl.TexMagFilter
import com.lehaine.littlekt.graphics.gl.TexMinFilter
import com.lehaine.littlekt.input.Key
import com.lehaine.littlekt.log.Logger
import com.lehaine.littlekt.math.Mat4
import kotlin.time.Duration

/**
 * @author Colton Daily
 * @date 11/6/2021
 */
class FrameBufferTest(context: Context) : ContextListener(context) {

    val batch = SpriteBatch(context)
    val fbo = FrameBuffer(240, 135).also {
        it.prepare(context)
        it.colorBufferTexture.apply {
            magFilter = TexMagFilter.NEAREST
            minFilter = TexMinFilter.NEAREST
        }
    }
    var loading = true

    lateinit var texture: Texture

    var projection = Mat4().setOrthographic(
        left = 0f,
        right = 240f,
        bottom = 0f,
        top = 135f,
        near = -1f,
        far = 1f
    )
    val camera = OrthographicCamera(graphics.width, graphics.height)
    private var x = 0f
    private var y = 0f

    private var xVel = 0f
    private var yVel = 0f

    init {
        logger.level = Logger.Level.DEBUG
        vfs.launch {
            texture = resourcesVfs["person.png"].readTexture()
            loading = false
        }
        camera.translate(graphics.width / 2f, graphics.height / 2f, 0f)
    }

    override fun render(dt: Duration) {
        if (loading) {
            return
        }
        xVel = 0f
        yVel = 0f

        if (input.isKeyPressed(Key.W)) {
            yVel -= 1f
        }
        if (input.isKeyPressed(Key.S)) {
            yVel += 1f
        }
        if (input.isKeyPressed(Key.A)) {
            xVel -= 1f
        }
        if (input.isKeyPressed(Key.D)) {
            xVel += 1f
        }
        gl.clearColor(Color.DARK_GRAY)

        fbo.begin()
        gl.clearColor(Color.CLEAR)
        gl.clear(ClearBufferMask.COLOR_BUFFER_BIT)
        batch.use(projection) {
            it.color = Color.WHITE
            it.draw(texture, x, y)
            it.draw(texture, x, y + 25f)
        }
        fbo.end()

        camera.update()
        batch.use(camera.viewProjection) {
            it.color = Color.WHITE.withAlpha(0.8f)
            it.draw(fbo.colorBufferTexture, 0f, 0f, scaleX = 4f, scaleY = 4f)

            it.color = Color.WHITE
            it.draw(texture, 100f, 50f)
        }
        x += xVel
        y += yVel

        if (input.isKeyJustPressed(Key.P)) {
            logger.debug { stats }
        }

        if (input.isKeyJustPressed(Key.ESCAPE)) {
            close()
        }

    }

    override fun resize(width: Int, height: Int) {
        logger.debug { "Resize to $width,$height" }
    }

    override fun dispose() {
        texture.dispose()
        batch.dispose()
        fbo.dispose()
    }
}
