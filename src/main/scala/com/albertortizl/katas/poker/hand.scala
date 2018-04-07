package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.EitherSequence._


case class Hand(cards: List[Card])

//case class Ranked private (override val cards: List[Card]) extends Hand(cards)

object Hand {

  val BlankSpace = " "

  def parse(line: String): Either[String, Hand] = {
    val sequenceOfEither: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    val eitherOfList: Either[String, List[Card]] = sequenceOfEither.toList.sequence
    eitherOfList.right.map(Hand(_))
  }
}

