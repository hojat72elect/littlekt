// Generated by jextract

package com.littlekt.wgpu;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct WGPUBindGroupLayoutEntry {
 *     const WGPUChainedStruct *nextInChain;
 *     uint32_t binding;
 *     WGPUShaderStageFlags visibility;
 *     WGPUBufferBindingLayout buffer;
 *     WGPUSamplerBindingLayout sampler;
 *     WGPUTextureBindingLayout texture;
 *     WGPUStorageTextureBindingLayout storageTexture;
 * }
 * }
 */
public class WGPUBindGroupLayoutEntry {

    WGPUBindGroupLayoutEntry() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        WGPU.C_POINTER.withName("nextInChain"),
        WGPU.C_INT.withName("binding"),
        WGPU.C_INT.withName("visibility"),
        WGPUBufferBindingLayout.layout().withName("buffer"),
        WGPUSamplerBindingLayout.layout().withName("sampler"),
        WGPUTextureBindingLayout.layout().withName("texture"),
        WGPUStorageTextureBindingLayout.layout().withName("storageTexture")
    ).withName("WGPUBindGroupLayoutEntry");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout nextInChain$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("nextInChain"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static final AddressLayout nextInChain$layout() {
        return nextInChain$LAYOUT;
    }

    private static final long nextInChain$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static final long nextInChain$offset() {
        return nextInChain$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static MemorySegment nextInChain(MemorySegment struct) {
        return struct.get(nextInChain$LAYOUT, nextInChain$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static void nextInChain(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(nextInChain$LAYOUT, nextInChain$OFFSET, fieldValue);
    }

    private static final OfInt binding$LAYOUT = (OfInt)$LAYOUT.select(groupElement("binding"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t binding
     * }
     */
    public static final OfInt binding$layout() {
        return binding$LAYOUT;
    }

    private static final long binding$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t binding
     * }
     */
    public static final long binding$offset() {
        return binding$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t binding
     * }
     */
    public static int binding(MemorySegment struct) {
        return struct.get(binding$LAYOUT, binding$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t binding
     * }
     */
    public static void binding(MemorySegment struct, int fieldValue) {
        struct.set(binding$LAYOUT, binding$OFFSET, fieldValue);
    }

    private static final OfInt visibility$LAYOUT = (OfInt)$LAYOUT.select(groupElement("visibility"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUShaderStageFlags visibility
     * }
     */
    public static final OfInt visibility$layout() {
        return visibility$LAYOUT;
    }

    private static final long visibility$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUShaderStageFlags visibility
     * }
     */
    public static final long visibility$offset() {
        return visibility$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUShaderStageFlags visibility
     * }
     */
    public static int visibility(MemorySegment struct) {
        return struct.get(visibility$LAYOUT, visibility$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUShaderStageFlags visibility
     * }
     */
    public static void visibility(MemorySegment struct, int fieldValue) {
        struct.set(visibility$LAYOUT, visibility$OFFSET, fieldValue);
    }

    private static final GroupLayout buffer$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("buffer"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUBufferBindingLayout buffer
     * }
     */
    public static final GroupLayout buffer$layout() {
        return buffer$LAYOUT;
    }

    private static final long buffer$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUBufferBindingLayout buffer
     * }
     */
    public static final long buffer$offset() {
        return buffer$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUBufferBindingLayout buffer
     * }
     */
    public static MemorySegment buffer(MemorySegment struct) {
        return struct.asSlice(buffer$OFFSET, buffer$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUBufferBindingLayout buffer
     * }
     */
    public static void buffer(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, buffer$OFFSET, buffer$LAYOUT.byteSize());
    }

    private static final GroupLayout sampler$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("sampler"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUSamplerBindingLayout sampler
     * }
     */
    public static final GroupLayout sampler$layout() {
        return sampler$LAYOUT;
    }

    private static final long sampler$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUSamplerBindingLayout sampler
     * }
     */
    public static final long sampler$offset() {
        return sampler$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUSamplerBindingLayout sampler
     * }
     */
    public static MemorySegment sampler(MemorySegment struct) {
        return struct.asSlice(sampler$OFFSET, sampler$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUSamplerBindingLayout sampler
     * }
     */
    public static void sampler(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, sampler$OFFSET, sampler$LAYOUT.byteSize());
    }

    private static final GroupLayout texture$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("texture"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUTextureBindingLayout texture
     * }
     */
    public static final GroupLayout texture$layout() {
        return texture$LAYOUT;
    }

    private static final long texture$OFFSET = 56;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUTextureBindingLayout texture
     * }
     */
    public static final long texture$offset() {
        return texture$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUTextureBindingLayout texture
     * }
     */
    public static MemorySegment texture(MemorySegment struct) {
        return struct.asSlice(texture$OFFSET, texture$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUTextureBindingLayout texture
     * }
     */
    public static void texture(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, texture$OFFSET, texture$LAYOUT.byteSize());
    }

    private static final GroupLayout storageTexture$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("storageTexture"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUStorageTextureBindingLayout storageTexture
     * }
     */
    public static final GroupLayout storageTexture$layout() {
        return storageTexture$LAYOUT;
    }

    private static final long storageTexture$OFFSET = 80;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUStorageTextureBindingLayout storageTexture
     * }
     */
    public static final long storageTexture$offset() {
        return storageTexture$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUStorageTextureBindingLayout storageTexture
     * }
     */
    public static MemorySegment storageTexture(MemorySegment struct) {
        return struct.asSlice(storageTexture$OFFSET, storageTexture$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUStorageTextureBindingLayout storageTexture
     * }
     */
    public static void storageTexture(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, storageTexture$OFFSET, storageTexture$LAYOUT.byteSize());
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}
