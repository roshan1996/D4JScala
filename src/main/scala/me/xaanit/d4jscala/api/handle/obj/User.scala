package me.xaanit.d4jscala.api.handle.obj

import java.awt.Color
import java.util.function.LongPredicate

import com.koloboke.function.LongObjConsumer
import me.xaanit.d4jscala.util.Queue
import me.xaanit.d4jscala._
import me.xaanit.d4jscala.api.DiscordClient
import sx.blah.discord.api.internal.DiscordClientImpl
import sx.blah.discord.handle.obj._
import sx.blah.discord.util.cache.{Cache, LongMap}

import scala.collection.mutable

class User(private[api] val user: IUser) {
  def getAvatarURL: String = user.getAvatarURL

  def isBot: Boolean = user.isBot

  def getName: String = user.getName

  def getAvatar: String = user.getAvatar

  def getOrCreatePMChannel(): Queue[PrivateChannel] = Queue(() => user.getOrCreatePMChannel().toWrappedChannel)

  def mention(): String = user.mention

  def mention(mentionWithNickname: Boolean): String = user.mention(mentionWithNickname)

  def moveToVoiceChannel(channel: VoiceChannel): Queue[Unit] = Queue(() => user.moveToVoiceChannel(channel.channel))

  def getVoiceStates: Map[Long, VoiceState] = {
    val states: LongMap[IVoiceState] = user.getVoiceStates
    val map: mutable.Map[Long, VoiceState] = mutable.Map()
    states.keySet.forEachWhile(value => {
      map += value -> states.get(value).toWrappedState
      true
    })
    map.toMap[Long, VoiceState]
  }

  def getVoiceStateForGuild(guild: Guild): VoiceState = user.getVoiceStateForGuild(guild.guild).toWrappedState

  def getColorForGuild(guild: Guild): Color = user.getColorForGuild(guild.guild)

  def removeRole(role: Role): Queue[Unit] = Queue(() => user.removeRole(role.role))

  def getRolesForGuild(guild: Guild): List[Role] = user.getRolesForGuild(guild.guild).toWrappedList(_.toWrappedRole)

  def getPresence: Presence = user.getPresence.toWrappedPresence

  def getNicknameForGuild(guild: Guild): Option[String] = {
    user.getNicknameForGuild(guild.guild) match {
      case null => None
      case x => Some[String](x)
    }
  }

  def getDiscriminator: String = user.getDiscriminator

  def getDisplayName(guild: Guild): String = user.getDisplayName(guild.guild)

  def getPermissionsForGuild(guild: Guild): Set[Permissions] = user.getPermissionsForGuild(guild.guild).toSet

  def addRole(role: Role): Queue[Unit] = Queue(() => user.addRole(role.role))

  def getShard: Shard = user.getShard.toWrappedShard

  def copy(): User = user.copy.toWrappedUser

  def getClient: DiscordClient = user.getClient.toWrappedClient

  def getLongID: Long = user.getLongID
}

object User {
  def apply(user: IUser): User = new User(user)
}
