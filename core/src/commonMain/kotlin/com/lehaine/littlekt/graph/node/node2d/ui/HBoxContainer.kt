package com.lehaine.littlekt.graph.node.node2d.ui

import com.lehaine.littlekt.graph.SceneGraph
import com.lehaine.littlekt.graph.node.Node
import com.lehaine.littlekt.graph.node.addTo
import com.lehaine.littlekt.graph.node.annotation.SceneGraphDslMarker

/**
 * Adds a [HBoxContainer] to the current [Node] as a child and then triggers the [callback]
 */
inline fun Node.hBoxContainer(callback: @SceneGraphDslMarker HBoxContainer.() -> Unit = {}) =
    HBoxContainer().also(callback).addTo(this)
/**
 * Adds a [HBoxContainer] to the current [SceneGraph.root] as a child and then triggers the [callback]
 */
inline fun SceneGraph.hBoxContainer(callback: @SceneGraphDslMarker HBoxContainer.() -> Unit = {}) =
    root.hBoxContainer(callback)

/**
 * @author Colton Daily
 * @date 1/2/2022
 */
open class HBoxContainer : BoxContainer() {

    init {
        vertical = false
    }
}