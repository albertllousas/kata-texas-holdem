package com.albertortizl.katas.poker

case class Hand(cards: List[Card])

//case class Ranked private (override val cards: List[Card]) extends Hand(cards)

object Hand {

  val BlankSpace = " "

  def parse(hand: String): Either[String,Hand] = {

    val sequenceOfErrorOrCard: Seq[Either[String, Card]] = hand split BlankSpace map Card.parse
    val errorOrCards: Either[String, List[Card]] =
      sequenceOfErrorOrCard.foldRight(Right(Nil): Either[String, List[Card]]) {
      (either, acc) => {
        either.right.flatMap(card => acc.right.map(cards => card :: cards))
      }
    }
    errorOrCards.right.map(cards => Hand(cards))
  }
}

