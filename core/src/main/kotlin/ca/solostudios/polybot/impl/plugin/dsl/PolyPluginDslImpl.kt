/*
 * PolyBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2022-2022 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file PolyPluginDslImpl.kt is part of PolyBot
 * Last modified on 22-11-2022 03:06 p.m.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * POLYBOT IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ca.solostudios.polybot.impl.plugin.dsl

import ca.solostudios.polybot.api.cloud.CommandManager
import ca.solostudios.polybot.api.plugin.dsl.PolyPluginDsl
import ca.solostudios.polybot.api.plugin.dsl.command.AnnotationParser
import ca.solostudios.polybot.api.plugin.dsl.command.PolyCommandDsl
import ca.solostudios.polybot.api.plugin.dsl.event.PolyEventDsl
import ca.solostudios.polybot.api.plugin.dsl.service.PolyServiceDsl
import ca.solostudios.polybot.impl.plugin.dsl.command.PolyCommandDslImpl
import ca.solostudios.polybot.impl.plugin.dsl.service.PolyServiceDslImpl
import com.uchuhimo.konf.ConfigSpec

internal class PolyPluginDslImpl(
        val cloud: CommandManager,
        annotationParser: AnnotationParser
                                ) : PolyPluginDsl {
    val commandDsl = PolyCommandDslImpl(cloud, annotationParser)
    val serviceDsl = PolyServiceDslImpl()
    val configSpecs = mutableListOf<ConfigSpec>()
    
    override fun commands(block: PolyCommandDsl.() -> Unit) {
        commandDsl.block()
    }
    
    override fun services(block: PolyServiceDsl.() -> Unit) {
        serviceDsl.block()
    }
    
    override fun events(block: PolyEventDsl.() -> Unit) {
        TODO("Not yet implemented")
    }
    
    override fun configSpec(configSpec: ConfigSpec) {
        configSpecs += configSpec
    }
}