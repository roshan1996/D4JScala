package me.xaanit.d4jscala.api.handle.obj

import sx.blah.discord.handle.obj.IEmbed

case class Embed(embed: IEmbed) {
  def getEmbedProvider = ???

  def getEmbedFields = ???

  def getAuthor = ???

  def getVideo = ???

  def getThumbnail = ???

  def getDescription = ???

  def getTitle = ???

  def getTimestamp = ???

  def getFooter = ???

  def getColor = ???

  def getUrl = ???

  def getType = ???

  def getImage = ???
}

object Embed {
  def apply(embed: IEmbed): Embed = new Embed(embed)
}