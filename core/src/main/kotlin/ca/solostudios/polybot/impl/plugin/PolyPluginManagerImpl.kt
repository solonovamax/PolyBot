/*
 * PolyBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2022-2022 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file PolyPluginManagerImpl.kt is part of PolyBot
 * Last modified on 26-06-2022 04:42 p.m.
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

package ca.solostudios.polybot.impl.plugin

import ca.solostudios.polybot.api.PolyBot
import ca.solostudios.polybot.api.plugin.PolyPlugin
import ca.solostudios.polybot.api.plugin.PolyPluginManager
import ca.solostudios.polybot.api.plugin.info.PolyPluginInfo
import ca.solostudios.polybot.common.service.Service
import ca.solostudios.polybot.common.service.ServiceManager
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.DIAware
import kotlin.reflect.KClass
import kotlin.time.Duration

public class PolyPluginManagerImpl(
        override val polybot: PolyBot
                                  ) : PolyPluginManager, DIAware, CoroutineScope by polybot {
    override val di: DI = polybot.di
    
    override val plugins: MutableMap<PolyPluginInfo, PolyPlugin> = mutableMapOf()
    
    override suspend fun loadPlugins() {
        val pluginsPath = polybot.directory("plugins")
        
        TODO("Finish implementation")
    }
    
    override fun <T : Service> addService(service: T, clazz: KClass<T>) {
        TODO("Not yet implemented")
    }
    
    @Deprecated("Avoid using any service methods with the plugin manager, use the plugin methods instead.")
    override fun <T : Service> getService(clazz: KClass<T>): T {
        TODO("Not yet implemented")
    }
    
    override fun <T : Service> addException(serviceClass: KClass<T>, exception: Exception) {
        TODO("Not yet implemented")
    }
    
    override val services: List<Service>
        get() = TODO("Not yet implemented")
    override val startupTimes: List<Pair<Service, Duration>>
        get() = TODO("Not yet implemented")
    override val serviceHealth: List<ServiceManager.ServiceHealth<*>>
        get() = TODO("Not yet implemented")
    override val state: Service.State
        get() = TODO("Not yet implemented")
    override val shutdown: Boolean
        get() = TODO("Not yet implemented")
    override val running: Boolean
        get() = TODO("Not yet implemented")
    override val active: Boolean
        get() = TODO("Not yet implemented")
    override val healthy: Boolean
        get() = TODO("Not yet implemented")
    
    override suspend fun shutdown() {
        TODO("Not yet implemented")
    }
    
    override suspend fun start() {
        TODO("Not yet implemented")
    }
}