/*
 * PolyhedralBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2021-2021 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file MessageCacheCommands.kt is part of PolyhedralBot
 * Last modified on 03-10-2021 06:50 p.m.
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
 * POLYHEDRALBOT IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.solostudios.polybot.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.Hidden
import com.solostudios.polybot.Constants
import com.solostudios.polybot.PolyBot
import com.solostudios.polybot.cloud.commands.PolyCommandContainer
import com.solostudios.polybot.cloud.commands.PolyCommands
import com.solostudios.polybot.cloud.commands.annotations.JDAUserPermission
import com.solostudios.polybot.cloud.commands.annotations.PolyCategory
import com.solostudios.polybot.entities.PolyMessage
import com.solostudios.polybot.util.idFooter
import dev.minn.jda.ktx.Embed
import dev.minn.jda.ktx.await
import kotlinx.coroutines.launch
import org.slf4j.kotlin.*

@Hidden
@PolyCategory(BOT_ADMIN_CATEGORY)
@PolyCommandContainer
class MessageCacheCommands(bot: PolyBot) : PolyCommands(bot) {
    private val logger by getLogger()
    
    @CommandDescription("Get message from cache")
    @CommandMethod("cache|msg-cache <id>")
    @JDAUserPermission(ownerOnly = true)
    fun messageFromCache(message: PolyMessage,
                         @Argument("id")
                         id: Long) {
        bot.scope.launch {
            val cachedMessage = bot.cacheManager.messageCache.getMessage(id)
            
            if (cachedMessage != null) {
                logger.info(cachedMessage) { "here is the message {}" }
                
                val embed = Embed {
                    author {
                        name = "${cachedMessage.username}#${cachedMessage.discriminator}"
                        url = cachedMessage.url
                        iconUrl = bot.jda.retrieveUserById(cachedMessage.author)
                                .map { it.effectiveAvatarUrl }
                                .onErrorMap { null }
                                .await() ?: Constants.defaultAvatarUrl
                    }
                    color = 0x2ECC70
                    
                    description = "**<@${cachedMessage.author}> sent a message in <#${cachedMessage.channel}>.**\n${cachedMessage.content}"
                    
                    idFooter(message.timeCreated, message.guild, message.channel, message.author, message)
                }
                
                message.reply(embed)
            }
        }
    }
}
