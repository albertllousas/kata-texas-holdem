package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.EitherSequence._


class Hand(val cards: List[Card]) {
  def fold():Fold = Fold(cards)
  def rankWith(ranking:HandRanking):Ranked = Ranked(cards, ranking)
  override def toString: String = s"Hand($cards)"
}
case class Fold private (override val cards: List[Card]) extends Hand(cards)
case class Ranked private (override val cards: List[Card], handRanking: HandRanking) extends Hand(cards)

object Hand {

  val BlankSpace = " "

  def apply(cards:List[Card]) = new Hand(cards)

  def parse(line: String): Either[String, Hand] = {
    val sequenceOfEither: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    val eitherOfList: Either[String, List[Card]] = sequenceOfEither.toList.sequence
    eitherOfList.right.map(Hand(_))
  }
}

