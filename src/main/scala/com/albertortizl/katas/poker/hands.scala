package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.implicits._

sealed trait CardSet
case class HoleCards(first: Card, second: Card) extends CardSet
case class Hand(first: Card, second: Card, third: Card, fourth: Card, fifth: Card) extends CardSet
case class PlayerCards(holeCards: HoleCards, communityCards: List[Card]) extends CardSet

sealed trait HandState
case class Alive(playerCards: PlayerCards) extends HandState
case class Folded(playerCards: PlayerCards) extends HandState
case class Finalist private(playerCards: PlayerCards, handRanking: HandRanking, hand: Hand) extends HandState
case class Winner private(playerCards: PlayerCards, handRanking: HandRanking, hand: Hand) extends HandState

object PlayerCards {

  val BlankSpace = " "

  def parse(line: String): Either[String, PlayerCards] = {

    val sequenceOfEither: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    val eitherOfList: Either[String, List[Card]] = sequenceOfEither.toList.sequence
    eitherOfList.flatMap {
      case p1 :: p2 :: communityCards if valid(communityCards) => Right(PlayerCards(HoleCards(p1, p2), communityCards))
      case _ => Left(s"'$line' are not a valid set of cards")
    }
  }

  private def valid(communityCards: List[Card]) = {
    communityCards.lengthCompare(5) <= 0
  }
}

