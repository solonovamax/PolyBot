/*
 * PolyBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2022-2022 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file ClasspathCandidateFinder.kt is part of PolyBot
 * Last modified on 30-07-2022 06:20 p.m.
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

package ca.solostudios.polybot.api.plugin.finder

import ca.solostudios.polybot.api.PolyBot
import ca.solostudios.polybot.api.plugin.info.PluginInfo
import ca.solostudios.polybot.impl.util.resolveCodeSource
import java.nio.file.Path
import kotlin.streams.toList

public class ClasspathCandidateFinder : PluginCandidateFinder {
    private val classLoader: ClassLoader
        get() = PolyBot::class.java.classLoader
    
    override fun findCandidates(): List<Path> {
        return classLoader.resources(PluginInfo.PLUGIN_INFO_FILE)
                .map {
                    it.resolveCodeSource(PluginInfo.PLUGIN_INFO_FILE).toRealPath()
                }
                .toList()
    }
}