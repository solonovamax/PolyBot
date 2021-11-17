/*
 * PolyhedralBot - A Discord bot for the Polyhedral Development discord server
 * Copyright (c) 2021-2021 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file BotAdminCommands.kt is part of PolyhedralBot
 * Last modified on 17-11-2021 02:51 p.m.
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

package ca.solostudios.polybot.commands

import ca.solostudios.polybot.ExitCodes
import ca.solostudios.polybot.PolyBot
import ca.solostudios.polybot.cloud.commands.PolyCommandContainer
import ca.solostudios.polybot.cloud.commands.PolyCommands
import ca.solostudios.polybot.cloud.commands.annotations.CommandName
import ca.solostudios.polybot.cloud.commands.annotations.JDAUserPermission
import ca.solostudios.polybot.cloud.commands.annotations.PolyCategory
import ca.solostudios.polybot.entities.PolyMessage
import ca.solostudios.polybot.entities.PolyUser
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.Hidden
import org.kodein.di.DI
import org.kodein.di.instance
import org.slf4j.kotlin.*

@Hidden
@PolyCommandContainer
@PolyCategory(BOT_ADMIN_CATEGORY)
class BotAdminCommands(di: DI) : PolyCommands(di) {
    private val logger by getLogger()
    
    private val bot: PolyBot by instance()
    
    @CommandName("Shutdown Bot")
    @CommandMethod("shutdown")
    @JDAUserPermission(ownerOnly = true)
    @CommandDescription("Shutdown PolyBot from within discord.\nNOTE: This will **NOT** restart the bot afterwards.")
    suspend fun shutdown(message: PolyMessage, author: PolyUser) {
        message.reply("Shutting down PolyBot")
        logger.info { "Shutdown request was triggered by ${author.tag} (${author.id})" }
        bot.shutdown(ExitCodes.EXIT_CODE_SHUTDOWN)
    }
    
    @CommandName("Restart Bot")
    @CommandMethod("restart")
    @JDAUserPermission(ownerOnly = true)
    @CommandDescription("Restart PolyBot from within discord.\nThe bot process will exit then start up again.")
    suspend fun restart(message: PolyMessage, author: PolyUser) {
        message.reply("Restarting PolyBot")
        logger.info { "Restart request was triggered by ${author.tag} (${author.id})" }
        bot.shutdown(ExitCodes.EXIT_CODE_RESTART)
    }
    
    @CommandName("Update Bot")
    @CommandMethod("update")
    @JDAUserPermission(ownerOnly = true)
    @CommandDescription("Update PolyBot from within discord.\nThe bot process will exit, a new jar will be downloaded, and then the bot will start again.")
    suspend fun update(message: PolyMessage, author: PolyUser) {
        message.reply("Updating PolyBot")
        logger.info { "Update request was triggered by ${author.tag} (${author.id})" }
        bot.shutdown(ExitCodes.EXIT_CODE_UPDATE)
    }
}