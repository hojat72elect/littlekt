package com.littlekt.log

/**
 * @author Colton Daily
 * @date 11/25/2021
 */
expect object DefaultLogOutput : Logger.Output {
    override fun output(logger: Logger, level: Logger.Level, msg: Any?)
}
