package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.implicits._

case class HoleCards(first: Card, second: Card)
case class Hand(cards: List[Card], ranking: HandRanking)
case class Player(holeCards: HoleCards, communityCards: List[Card], fold:Boolean) {
  def allCards = holeCards.first :: holeCards.second :: communityCards
}


object Player {

  val BlankSpace = " "

  def parse(line: String): Either[String, Player] = {

    val sequenceOfEither: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    val eitherOfList: Either[String, List[Card]] = sequenceOfEither.toList.sequence
    eitherOfList.flatMap {
      case p1 :: p2 :: communityCards if isFold(communityCards)  =>
        Right(Player(HoleCards(p1, p2), communityCards, fold = true))
      case p1 :: p2 :: communityCards if isAlive(communityCards) =>
        Right(Player(HoleCards(p1, p2), communityCards, fold = false))
      case _ =>
        Left(s"'$line' are not a valid set of cards")
    }
  }

  private def isAlive(communityCards: List[Card]) = communityCards.lengthCompare(5) == 0

  private def isFold(communityCards: List[Card]) = communityCards.lengthCompare(5) < 0

}
