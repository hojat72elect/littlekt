package com.lehaine.littlekt.io

import com.lehaine.littlekt.Application
import com.lehaine.littlekt.audio.AudioContext
import com.lehaine.littlekt.graphics.TextureData
import com.lehaine.littlekt.graphics.WebPixmap
import com.lehaine.littlekt.graphics.gl.PixmapTextureData
import com.lehaine.littlekt.log.Logger
import kotlinx.browser.window
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.xhr.ARRAYBUFFER
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType

/**
 * @author Colton Daily
 * @date 11/6/2021
 */
class WebFileHandler(
    application: Application,
    logger: Logger,
    val rootPath: String = window.location.protocol,
    val context2D: CanvasRenderingContext2D,
    val audioContext: AudioContext
) :
    BaseFileHandler(application, logger) {

    override fun read(filename: String): Content<String> {
        return asyncContent(filename) { it.toByteArray().decodeToString() }
    }

    override fun readData(filename: String): Content<ByteArray> {
        return asyncContent(filename) { it.toByteArray() }
    }

    override fun readTextureData(filename: String): Content<TextureData> {
        val img = Image()
        img.src = computeUrl(filename)

        val content = Content<TextureData>(filename, logger)

        // TODO - fix loading image element into Pixmap!!
        img.addEventListener(
            "load",
            object : EventListener {
                override fun handleEvent(event: Event) {
                    content.load(
                        PixmapTextureData(
                            WebPixmap(img.width, img.height, context2D, img), true
                        )
                    )
                }
            }
        )
        return content
    }

    override fun readSound(filename: String): Content<Sound> {
        return asyncContent(filename) { it }.flatMap { bytes ->
            val content = Content<Sound>(filename, logger)
            audioContext.decodeAudioData(bytes) { buffer ->
                content.load(WebSound(buffer, audioContext))
            }
            content
        }
    }

    // https://youtrack.jetbrains.com/issue/KT-30098
    fun ArrayBuffer.toByteArray(): ByteArray = Int8Array(this).unsafeCast<ByteArray>()

    private fun <T> asyncContent(filename: String, enc: (ArrayBuffer) -> T): Content<T> {
        val url = computeUrl(filename)

        val jsonFile = XMLHttpRequest()
        jsonFile.responseType = XMLHttpRequestResponseType.Companion.ARRAYBUFFER
        jsonFile.open("GET", url, true)

        val content = Content<T>(filename, logger)

        jsonFile.onload = { _ ->
            if (jsonFile.readyState == 4.toShort() && jsonFile.status == 200.toShort()) {
                val element = enc(jsonFile.response as ArrayBuffer)
                content.load(element)
            }
        }

        jsonFile.send()
        return content
    }

    private fun computeUrl(filename: String): String = rootPath + filename

}