package com.lehaine.littlekt.util.datastructure

import com.lehaine.littlekt.util.datastructure.internal.arraycopy
import com.lehaine.littlekt.util.datastructure.internal.contentEquals
import com.lehaine.littlekt.util.datastructure.internal.contentHashCode
import kotlin.math.max

/**
 * Int growable ArrayList without boxing.
 */
@Suppress("UNCHECKED_CAST")
class IntArrayList(capacity: Int = 7) : Collection<Int> {
    companion object

    var data: IntArray = IntArray(capacity); private set
    internal val capacity: Int get() = data.size
    private var length: Int = 0
    override var size: Int
        get() = length
        set(value) {
            ensure(value)
            this.length = value
        }

    constructor(other: IntArrayList) : this() {
        add(other)
    }

    constructor(vararg other: Int) : this() {
        add(other)
    }

    fun ensure(count: Int) {
        if (length + count > data.size) {
            data = data.copyOf(max(length + count, data.size * 3))
        }
    }

    fun clear() {
        length = 0
    }

    fun add(v0: Int) {
        ensure(1)
        data[length++] = v0
    }

    fun add(v0: Int, v1: Int) {
        ensure(2)
        data[length++] = v0
        data[length++] = v1
    }

    fun add(v0: Int, v1: Int, v2: Int) {
        ensure(3)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
    }

    fun add(v0: Int, v1: Int, v2: Int, v3: Int) {
        ensure(4)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
    }

    fun add(v0: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
        ensure(5)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
        data[length++] = v4
    }

    fun add(v0: Int, v1: Int, v2: Int, v3: Int, v4: Int, v5: Int) {
        ensure(6)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
        data[length++] = v4
        data[length++] = v5
    }

    operator fun plusAssign(value: Int) = add(value)
    operator fun plusAssign(value: IntArray) = add(value)
    operator fun plusAssign(value: IntArrayList) = add(value)
    operator fun plusAssign(value: Iterable<Int>) = add(value)

    fun add(values: IntArray, offset: Int = 0, length: Int = values.size) {
        ensure(length)
        arraycopy(values, offset, data, this.size, length)
        this.size += length
    }

    fun add(values: IntArrayList) = add(values.data, 0, values.size)
    fun add(values: Iterable<Int>) {
        for (v in values) add(v)
    }

    fun pop() = data[--size]

    operator fun get(index: Int): Int = data[index]

    /** Gets an item of the list without boxing */
    fun getAt(index: Int): Int = data[index]

    fun setAt(index: Int, value: Int): Int = value.also { set(index, value) }

    operator fun set(index: Int, value: Int) {
        if (index >= length) {
            ensure(index + 1)
            length = index + 1
        }
        data[index] = value
    }

    override fun iterator(): Iterator<Int> = listIterator(0)

    override fun contains(element: Int): Boolean {
        for (n in 0 until length) if (this.data[n] == element) return true
        return false
    }

    override fun containsAll(elements: Collection<Int>): Boolean {
        for (e in elements) if (!contains(e)) return false
        return true
    }

    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
    override fun isEmpty(): Boolean = this.size == 0

    fun indexOf(value: Int, start: Int = 0, end: Int = this.size): Int {
        for (n in start until end) if (data[n] == value) return n
        return -1
    }

    fun lastIndexOf(value: Int, start: Int = 0, end: Int = this.size): Int {
        for (n in (end - 1) downTo start) if (data[n] == value) return n
        return -1
    }

    fun insertAt(index: Int, value: Int) = this.apply {
        ensure(1)
        if (isNotEmpty()) arraycopy(data, index, data, index + 1, length - index)
        data[index] = value
        length++
    }

    fun insertAt(index: Int, value: IntArray, start: Int = 0, end: Int = value.size) = this.apply {
        val count = end - start
        ensure(count)
        if (isNotEmpty()) arraycopy(data, index, data, index + count, length - index)
        for (n in 0 until count) data[index + n] = value[start + n]
        length += count
    }

    fun swap(indexA: Int, indexB: Int) {
        val l = this.getAt(indexA)
        val r = this.getAt(indexB)
        this[indexA] = r
        this[indexB] = l
    }

    fun removeAt(index: Int): Int = removeAt(index, 1)

    fun removeAt(index: Int, count: Int): Int {
        if (index < 0 || index >= length || index + count > length) throw IndexOutOfBoundsException()
        val out = data[index]
        if (count > 0) {
            if (index < length - count) arraycopy(data, index + count, data, index, length - index - count)
            length -= count
        }
        return out
    }

    fun toIntArray() = this.data.copyOf(length)
    fun toShortArray() = ShortArray(length) { this.data[it].toShort() }

    // List interface

    fun indexOf(element: Int): Int = indexOf(element, 0, size)
    fun lastIndexOf(element: Int): Int = lastIndexOf(element, 0, size)

    fun listIterator(): ListIterator<Int> = listIterator(0)
    fun listIterator(index: Int): ListIterator<Int> = data.take(length).listIterator(index)
    fun subList(fromIndex: Int, toIndex: Int): List<Int> = data.asList().subList(fromIndex, toIndex)

    // Data
    override fun hashCode(): Int = data.contentHashCode(0, size)
    override fun equals(other: Any?): Boolean {
        if (other is IntArrayList) return size == other.size && data.contentEquals(other.data, 0, size)
        if (other is List<*>) return other == this
        return false
    }

    override fun toString(): String = StringBuilder(2 + 5 * size).also { sb ->
        sb.append('[')
        for (n in 0 until size) {
            if (n != 0) sb.append(", ")
            sb.append(this.getAt(n))
        }
        sb.append(']')
    }.toString()
}

fun intArrayListOf(vararg values: Int) = IntArrayList(*values)
fun IntArray.toIntArrayList() = IntArrayList(*this)


// Double

/**
 * Double growable ArrayList without boxing.
 */
@Suppress("UNCHECKED_CAST")
class DoubleArrayList(capacity: Int = 7) : Collection<Double> {
    companion object

    var data: DoubleArray = DoubleArray(capacity) as DoubleArray; private set
    internal val capacity: Int get() = data.size
    private var length: Int = 0
    override var size: Int
        get() = length
        set(value) {
            ensure(value)
            this.length = value
        }

    constructor(other: DoubleArrayList) : this() {
        add(other)
    }

    constructor(vararg other: Double) : this() {
        add(other)
    }

    private fun ensure(count: Int) {
        if (length + count > data.size) {
            data = data.copyOf(max(length + count, data.size * 3)) as DoubleArray
        }
    }

    fun clear() {
        length = 0
    }

    fun add(v0: Double) {
        ensure(1)
        data[length++] = v0
    }

    fun add(v0: Double, v1: Double) {
        ensure(2)
        data[length++] = v0
        data[length++] = v1
    }

    fun add(v0: Double, v1: Double, v2: Double) {
        ensure(3)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
    }

    fun add(v0: Double, v1: Double, v2: Double, v3: Double) {
        ensure(4)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
    }

    fun add(v0: Double, v1: Double, v2: Double, v3: Double, v4: Double) {
        ensure(5)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
        data[length++] = v4
    }

    fun add(v0: Double, v1: Double, v2: Double, v3: Double, v4: Double, v5: Double) {
        ensure(6)
        data[length++] = v0
        data[length++] = v1
        data[length++] = v2
        data[length++] = v3
        data[length++] = v4
        data[length++] = v5
    }

    operator fun plusAssign(value: Double) = add(value)
    operator fun plusAssign(value: DoubleArray) = add(value)
    operator fun plusAssign(value: DoubleArrayList) = add(value)
    operator fun plusAssign(value: Iterable<Double>) = add(value)

    fun add(values: DoubleArray, offset: Int = 0, length: Int = values.size) {
        ensure(length)
        arraycopy(values as DoubleArray, offset, data, this.size, length)
        this.size += length
    }

    fun add(values: DoubleArrayList) = add(values.data, 0, values.size)
    fun add(values: Iterable<Double>) {
        for (v in values) add(v)
    }

    fun pop() = data[--size]

    operator fun get(index: Int): Double = data[index]

    /** Gets an item of the list without boxing */
    fun getAt(index: Int): Double = data[index]

    fun setAt(index: Int, value: Double): Double = value.also { set(index, value) }

    operator fun set(index: Int, value: Double) {
        if (index >= length) {
            ensure(index + 1)
            length = index + 1
        }
        data[index] = value
    }

    override fun iterator(): Iterator<Double> = listIterator(0)

    override fun contains(element: Double): Boolean {
        for (n in 0 until length) if (this.data[n] == element) return true
        return false
    }

    override fun containsAll(elements: Collection<Double>): Boolean {
        for (e in elements) if (!contains(e)) return false
        return true
    }

    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
    override fun isEmpty(): Boolean = this.size == 0

    fun indexOf(value: Double, start: Int = 0, end: Int = this.size): Int {
        for (n in start until end) if (data[n] == value) return n
        return -1
    }

    fun lastIndexOf(value: Double, start: Int = 0, end: Int = this.size): Int {
        for (n in (end - 1) downTo start) if (data[n] == value) return n
        return -1
    }

    fun insertAt(index: Int, value: Double) = this.apply {
        ensure(1)
        if (isNotEmpty()) arraycopy(data, index, data, index + 1, length - index)
        data[index] = value
        length++
    }

    fun insertAt(index: Int, value: DoubleArray, start: Int = 0, end: Int = value.size) = this.apply {
        val count = end - start
        ensure(count)
        if (isNotEmpty()) arraycopy(data, index, data, index + count, length - index)
        for (n in 0 until count) data[index + n] = value[start + n]
        length += count
    }

    fun swap(indexA: Int, indexB: Int) {
        val l = this.getAt(indexA)
        val r = this.getAt(indexB)
        this[indexA] = r
        this[indexB] = l
    }

    fun removeAt(index: Int): Double = removeAt(index, 1)

    fun removeAt(index: Int, count: Int): Double {
        if (index < 0 || index >= length || index + count > length) throw IndexOutOfBoundsException()
        val out = data[index]
        if (count > 0) {
            if (index < length - count) arraycopy(data, index + count, data, index, length - index - count)
            length -= count
        }
        return out
    }

    fun toDoubleArray() = this.data.copyOf(length)

    // List interface

    fun indexOf(element: Double): Int = indexOf(element, 0, size)
    fun lastIndexOf(element: Double): Int = lastIndexOf(element, 0, size)

    fun listIterator(): ListIterator<Double> = listIterator(0)
    fun listIterator(index: Int): ListIterator<Double> = data.take(length).listIterator(index)
    fun subList(fromIndex: Int, toIndex: Int): List<Double> = data.asList().subList(fromIndex, toIndex)

    // Data
    override fun hashCode(): Int = data.contentHashCode(0, size)
    override fun equals(other: Any?): Boolean {
        if (other is DoubleArrayList) return size == other.size && data.contentEquals(other.data, 0, size)
        if (other is List<*>) return other == this
        return false
    }

    override fun toString(): String = StringBuilder(2 + 5 * size).also { sb ->
        sb.append('[')
        for (n in 0 until size) {
            if (n != 0) sb.append(", ")
            sb.append(this.getAt(n))
        }
        sb.append(']')
    }.toString()
}

fun doubleArrayListOf(vararg values: Double) = DoubleArrayList(*values)


// Float

/**
 * Float growable ArrayList without boxing.
 */
@Suppress("UNCHECKED_CAST")
class FloatArrayList(capacity: Int = 7) : Collection<Float> {
    companion object

    var data: FloatArray = FloatArray(capacity)
        private set
    internal val capacity: Int get() = data.size
    private var length: Int = 0
    override var size: Int
        get() = length
        set(value) {
            ensure(value)
            this.length = value
        }

    constructor(other: FloatArrayList) : this() {
        add(other)
    }

    constructor(vararg other: Float) : this() {
        add(other)
    }

    private fun ensure(count: Int) {
        if (length + count > data.size) {
            data = data.copyOf(max(length + count, data.size * 3)) as FloatArray
        }
    }

    fun clear() {
        length = 0
    }

    fun add(value: Float) {
        ensure(1)
        data[length++] = value
    }

    operator fun plusAssign(value: Float) = add(value)
    operator fun plusAssign(value: FloatArray) = add(value)
    operator fun plusAssign(value: FloatArrayList) = add(value)
    operator fun plusAssign(value: Iterable<Float>) = add(value)

    fun add(values: FloatArray, offset: Int = 0, length: Int = values.size) {
        ensure(length)
        arraycopy(values, offset, data, this.size, length)
        this.size += length
    }

    fun add(values: FloatArrayList) = add(values.data, 0, values.size)
    fun add(values: Iterable<Float>) {
        for (v in values) add(v)
    }

    fun pop() = data[--size]

    operator fun get(index: Int): Float = data[index]

    /** Gets an item of the list without boxing */
    fun getAt(index: Int): Float = data[index]

    fun setAt(index: Int, value: Float): Float = value.also { set(index, value) }

    operator fun set(index: Int, value: Float) {
        if (index >= length) {
            ensure(index + 1)
            length = index + 1
        }
        data[index] = value
    }

    override fun iterator(): Iterator<Float> = listIterator(0)

    override fun contains(element: Float): Boolean {
        for (n in 0 until length) if (this.data[n] == element) return true
        return false
    }

    override fun containsAll(elements: Collection<Float>): Boolean {
        for (e in elements) if (!contains(e)) return false
        return true
    }

    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
    override fun isEmpty(): Boolean = this.size == 0

    fun indexOf(value: Float, start: Int = 0, end: Int = this.size): Int {
        for (n in start until end) if (data[n] == value) return n
        return -1
    }

    fun lastIndexOf(value: Float, start: Int = 0, end: Int = this.size): Int {
        for (n in (end - 1) downTo start) if (data[n] == value) return n
        return -1
    }

    fun insertAt(index: Int, value: Float) = this.apply {
        ensure(1)
        if (isNotEmpty()) arraycopy(data, index, data, index + 1, length - index)
        data[index] = value
        length++
    }

    fun insertAt(index: Int, value: FloatArray, start: Int = 0, end: Int = value.size) = this.apply {
        val count = end - start
        ensure(count)
        if (isNotEmpty()) arraycopy(data, index, data, index + count, length - index)
        for (n in 0 until count) data[index + n] = value[start + n]
        length += count
    }

    fun swap(indexA: Int, indexB: Int) {
        val l = this.getAt(indexA)
        val r = this.getAt(indexB)
        this[indexA] = r
        this[indexB] = l
    }

    fun removeAt(index: Int): Float = removeAt(index, 1)

    fun removeAt(index: Int, count: Int): Float {
        if (index < 0 || index >= length || index + count > length) throw IndexOutOfBoundsException()
        val out = data[index]
        if (count > 0) {
            if (index < length - count) arraycopy(data, index + count, data, index, length - index - count)
            length -= count
        }
        return out
    }

    fun toFloatArray() = this.data.copyOf(length)

    // List interface

    fun indexOf(element: Float): Int = indexOf(element, 0, size)
    fun lastIndexOf(element: Float): Int = lastIndexOf(element, 0, size)

    fun listIterator(): ListIterator<Float> = listIterator(0)
    fun listIterator(index: Int): ListIterator<Float> = data.take(length).listIterator(index)
    fun subList(fromIndex: Int, toIndex: Int): List<Float> = data.asList().subList(fromIndex, toIndex)

    // Data
    override fun hashCode(): Int = data.contentHashCode(0, size)
    override fun equals(other: Any?): Boolean {
        if (other is FloatArrayList) return size == other.size && data.contentEquals(other.data, 0, size)
        if (other is List<*>) return other == this
        return false
    }

    override fun toString(): String = StringBuilder(2 + 5 * size).also { sb ->
        sb.append('[')
        for (n in 0 until size) {
            if (n != 0) sb.append(", ")
            sb.append(this.getAt(n))
        }
        sb.append(']')
    }.toString()
}

fun floatArrayListOf(vararg values: Float) = FloatArrayList(*values)
fun FloatArray.toFloatArrayList() = FloatArrayList(*this)