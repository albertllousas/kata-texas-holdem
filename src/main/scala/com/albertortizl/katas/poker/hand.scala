package com.albertortizl.katas.poker

case class Hand(cards: List[Card])

//case class Ranked private (override val cards: List[Card]) extends Hand(cards)

object Hand {

  val BlankSpace = " "

  def parse(line: String): Either[String,Hand] = {

    val sequenceOfErrorsOrCards: Seq[Either[String, Card]] = line split BlankSpace map Card.parse
    val errorOrListOfCards: Either[String, List[Card]] =
      sequenceOfErrorsOrCards.foldRight(Right(Nil): Either[String, List[Card]]) {
      (either, acc) => {
        either.right.flatMap(card => acc.right.map(cards => card :: cards))
      }
    }
    errorOrListOfCards.right.map(Hand(_))
  }
}

