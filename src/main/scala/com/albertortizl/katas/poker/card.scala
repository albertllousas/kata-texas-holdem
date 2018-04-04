package com.albertortizl.katas.poker

sealed abstract class Suite
case object Hearts extends Suite //♥
case object Diamonds extends Suite //♦
case object Spades extends Suite //♠
case object Clubs extends Suite //♣

sealed abstract class Rank
case object Two extends Rank
case object Three extends Rank
case object Four extends Rank
case object Five extends Rank
case object Six extends Rank
case object Seven extends Rank
case object Eight extends Rank
case object Nine extends Rank
case object Ten extends Rank
case object Jack extends Rank
case object Queen extends Rank
case object King extends Rank
case object Ace extends Rank

case class Card(rank: Rank, suite: Suite)

object Rank {

  private val mappings = List(
    (Two, "2"), (Three, "3"), (Four, "4"), (Five, "5"), (Six, "6"), (Seven, "7"),
    (Eight, "8"), (Nine, "9"), (Ten, "T"), (Jack, "J"), (Queen, "Q"), (King, "K"), (Ace, "A")
  )

  def parse(abbreviation: String): Either[String, Rank] =
    mappings
      .find(_._2 == abbreviation)
      .map(_._1)
      .map(Right(_))
      .getOrElse(Left(s"Error parsing abbreviation '$abbreviation', is not a valid rank"))

  def abbreviation(rank:Rank) : String =
    mappings
      .find(_._1 == rank)
      .map(_._2)
      .get // Rank is a sealed class, so it is totally safe call 'get' method here
}



