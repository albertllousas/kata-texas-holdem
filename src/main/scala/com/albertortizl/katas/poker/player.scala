package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.implicits._

case class HoleCards(first: Card, second: Card)
case class Player(holeCards: HoleCards, communityCards: List[Card]) {
  def fold: Boolean = communityCards.lengthCompare(5) < 0
  def allCards: List[Card] = holeCards.first :: holeCards.second :: communityCards
}


object Player extends DefaultParsing[Player]{

  val BlankSpace = " "
  def parse(line: String): Either[String, Player] = {
    val cards: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    cards.toList.sequence.flatMap {
      case firstPlayerCard :: secondPlayerCard :: communityCards if areValid(communityCards)  =>
        Right(Player(HoleCards(firstPlayerCard, secondPlayerCard), communityCards))
      case _ =>
        Left(s"'$line' are not a valid set of cards")
    }
  }

  private def areValid(communityCards: List[Card]) = communityCards.lengthCompare(5) <= 0

  def abbreviate(player: Player): String = s"${player.allCards.map(Card.abbreviate).mkString(BlankSpace)}"
}
