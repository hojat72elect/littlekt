package com.lehaine.littlekt.io

import org.lwjgl.BufferUtils

/**
 * @author Colton Daily
 * @date 11/19/2021
 */
typealias JByteBuffer = java.nio.ByteBuffer
typealias JFloatBuffer = java.nio.FloatBuffer
typealias JShortBuffer = java.nio.ShortBuffer
typealias JByteOrder = java.nio.ByteOrder

actual class ByteBuffer private constructor(val dw: JByteBuffer) : Buffer {

    actual override var limit: Int
        set(value) {
            dw.limit(value)
        }
        get() = dw.limit()
    actual override val remaining: Int get() = dw.remaining()
    actual override val capacity: Int get() = dw.capacity()
    actual override var position: Int
        set(value) {
            dw.position(value)
        }
        get() = dw.position()

    actual override val hasRemaining: Boolean get() = dw.hasRemaining()

    actual fun flip(): ByteBuffer {
        dw.flip()
        return this
    }

    actual fun mark(): ByteBuffer {
        dw.mark()
        return this
    }

    actual fun reset(): ByteBuffer {
        dw.reset()
        return this
    }

    actual fun order(order: ByteOrder): ByteBuffer {
        dw.order(if (order == ByteOrder.LITTLE_ENDIAN) JByteOrder.LITTLE_ENDIAN else JByteOrder.BIG_ENDIAN)
        return this
    }

    actual fun clear(): ByteBuffer {
        dw.clear()
        return this
    }

    actual fun get(): Byte = dw.get()
    actual fun get(index: Int): Byte = dw.get(index)
    actual fun get(dst: ByteArray, offset: Int, cnt: Int): Unit {
        dw.get(dst, offset, cnt)
    }

    actual fun getChar(): Char = dw.char
    actual fun getChar(index: Int): Char = dw.getChar(index)
    actual fun getShort(): Short = dw.short
    actual fun getShort(index: Int): Short = dw.getShort(index)
    actual fun getInt(): Int = dw.int
    actual fun getInt(index: Int): Int = dw.getInt(index)
    actual fun getLong(): Long = dw.long
    actual fun getLong(index: Int): Long = dw.getLong(index)
    actual fun getFloat(): Float = dw.float
    actual fun getFloat(index: Int): Float = dw.getFloat(index)
    actual fun getDouble(): Double = dw.double
    actual fun getDouble(index: Int): Double = dw.getDouble(index)

    actual fun put(value: Byte): ByteBuffer {
        dw.put(value)
        return this
    }

    actual fun put(value: Byte, index: Int): ByteBuffer {
        dw.put(index, value)
        return this
    }

    actual fun put(src: ByteArray): ByteBuffer {
        dw.put(src)
        return this
    }

    actual fun put(src: ByteArray, offset: Int, cnt: Int): ByteBuffer {
        dw.put(src, offset, cnt)
        return this
    }

    actual fun putChar(value: Char): ByteBuffer {
        dw.putChar(value)
        return this
    }

    actual fun putChar(value: Char, index: Int): ByteBuffer {
        dw.putChar(index, value)
        return this
    }

    actual fun putShort(value: Short): ByteBuffer {
        dw.putShort(value)
        return this
    }

    actual fun putShort(value: Short, index: Int): ByteBuffer {
        dw.putShort(index, value)
        return this
    }

    actual fun putInt(value: Int): ByteBuffer {
        dw.putInt(value)
        return this
    }

    actual fun putInt(value: Int, index: Int): ByteBuffer {
        dw.putInt(index, value)
        return this
    }

    actual fun putLong(value: Long): ByteBuffer {
        dw.putLong(value)
        return this
    }

    actual fun putLong(value: Long, index: Int): ByteBuffer {
        dw.putLong(index, value)
        return this
    }

    actual fun putFloat(value: Float): ByteBuffer {
        dw.putFloat(value)
        return this
    }

    actual fun putFloat(value: Float, index: Int): ByteBuffer {
        dw.putFloat(index, value)
        return this
    }

    actual fun putDouble(value: Double): ByteBuffer {
        dw.putDouble(value)
        return this
    }

    actual fun putDouble(value: Double, index: Int): ByteBuffer {
        dw.putDouble(index, value)
        return this
    }

    actual fun array(): ByteArray = dw.array()

    actual fun asFloatBuffer(): FloatBuffer = FloatBuffer.createFrom(dw.asFloatBuffer())

    actual companion object {
        actual fun allocate(capacity: Int) = ByteBuffer(BufferUtils.createByteBuffer(capacity))
    }
}


actual class FloatBuffer private constructor(val dw: JFloatBuffer) : Buffer {
    actual override var limit: Int
        set(value) {
            dw.limit(value)
        }
        get() = dw.limit()
    actual override val remaining: Int get() = dw.remaining()
    actual override val capacity: Int get() = dw.capacity()
    actual override var position: Int
        set(value) {
            dw.position(value)
        }
        get() = dw.position()

    actual override val hasRemaining: Boolean get() = dw.hasRemaining()

    actual fun flip(): FloatBuffer {
        dw.flip()
        return this
    }

    actual fun mark(): FloatBuffer {
        dw.mark()
        return this
    }

    actual fun reset(): FloatBuffer {
        dw.reset()
        return this
    }

    actual fun clear(): FloatBuffer {
        dw.clear()
        return this
    }

    actual fun get(): Float {
        return dw.get()
    }

    actual fun get(index: Int): Float {
        return dw.get(index)
    }

    actual fun get(dst: FloatArray, offset: Int, cnt: Int): FloatBuffer {
        dw.get(dst, offset, cnt)
        return this
    }

    actual fun put(value: Float): FloatBuffer {
        dw.put(value)
        return this
    }

    actual fun put(value: Float, index: Int): FloatBuffer {
        dw.put(index, value)
        return this
    }

    actual fun put(src: FloatArray): FloatBuffer {
        dw.put(src)
        return this
    }

    actual fun put(src: FloatArray, offset: Int, cnt: Int): FloatBuffer {
        dw.put(src, offset, cnt)
        return this
    }

    actual fun array(): FloatArray {
        return dw.array()
    }

    actual companion object {
        fun createFrom(buffer: JFloatBuffer) = FloatBuffer(buffer)
        actual fun allocate(capacity: Int) = FloatBuffer(BufferUtils.createFloatBuffer(capacity))
    }
}

actual class ShortBuffer private constructor(val dw: JShortBuffer) : Buffer {
    actual override var limit: Int
        set(value) {
            dw.limit(value)
        }
        get() = dw.limit()
    actual override val remaining: Int get() = dw.remaining()
    actual override val capacity: Int get() = dw.capacity()
    actual override var position: Int
        set(value) {
            dw.position(value)
        }
        get() = dw.position()

    actual override val hasRemaining: Boolean get() = dw.hasRemaining()

    actual fun flip(): ShortBuffer {
        dw.flip()
        return this
    }

    actual fun mark(): ShortBuffer {
        dw.mark()
        return this
    }

    actual fun reset(): ShortBuffer {
        dw.reset()
        return this
    }

    actual fun clear(): ShortBuffer {
        dw.clear()
        return this
    }

    actual fun get(): Short {
        return dw.get()
    }

    actual fun get(index: Int): Short {
        return dw.get(index)
    }

    actual fun get(dst: ShortArray, offset: Int, cnt: Int): ShortBuffer {
        dw.get(dst, offset, cnt)
        return this
    }

    actual fun put(value: Short): ShortBuffer {
        dw.put(value)
        return this
    }

    actual fun put(value: Short, index: Int): ShortBuffer {
        dw.put(index, value)
        return this
    }

    actual fun put(src: ShortArray): ShortBuffer {
        dw.put(src)
        return this
    }

    actual fun put(src: ShortArray, offset: Int, cnt: Int): ShortBuffer {
        dw.put(src, offset, cnt)
        return this
    }

    actual fun array(): ShortArray {
        return dw.array()
    }

    actual companion object {
        actual fun allocate(capacity: Int) = ShortBuffer(BufferUtils.createShortBuffer(capacity))
    }
}