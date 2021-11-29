package com.lehaine.littlekt.graphics.shader

import com.lehaine.littlekt.graphics.shader.generator.GlslGenerator
import com.lehaine.littlekt.graphics.shader.generator.delegate.BuiltinVarDelegate
import com.lehaine.littlekt.graphics.shader.generator.type.BoolResult
import com.lehaine.littlekt.graphics.shader.generator.type.vec.Vec2

/**
 * @author Colton Daily
 * @date 11/25/2021
 */
interface Shader {
    var source: String
    val parameters: List<ShaderParameter>
}

interface FragmentShader : Shader
interface VertexShader : Shader

abstract class FragmentShaderModel : GlslGenerator(), FragmentShader {
    var gl_FragCoord by BuiltinVarDelegate()
    var gl_FragColor by BuiltinVarDelegate()

    val gl_frontFacing = BoolResult("gl_frontFacing")

    override var source: String = ""
        get() {
            if (field.isBlank()) {
                field = generate()
            }
            return field
        }
}

abstract class VertexShaderModel : GlslGenerator(), VertexShader {
    var gl_Position by BuiltinVarDelegate()

    override var source: String = ""
        get() {
            if (field.isBlank()) {
                field = generate()
            }
            return field
        }
}


open class ShaderModel {
    inline fun fragment(crossinline src: FragmentShaderModel.() -> Unit): FragmentShaderModel {
        return object : FragmentShaderModel() {}.apply(src)
    }

    inline fun vertex(crossinline src: VertexShaderModel.() -> Unit): VertexShaderModel {
        return object : VertexShaderModel() {}.apply(src)
    }
}