package com.albertortizl.katas.poker

sealed abstract class Suite
case object Hearts extends Suite //♥
case object Diamonds extends Suite //♦
case object Spades extends Suite //♠
case object Clubs extends Suite //♣

sealed abstract class Rank {
  def of(suite:Suite) :Card = Card(this, suite)
}
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

object Deck {
  val suites: List[Suite] = List(Hearts, Diamonds, Spades, Clubs)
  val ranks: List[Rank] = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val cards: List[Card] = for (rank <- ranks; suite <- suites) yield Card(rank, suite)
}

object Suite {

  private val (h, d, s, c) = ("h", "d", "s", "c")

  def parse(str: String): Either[String, Suite] =
    str match {
      case `h` => Right(Hearts)
      case `d` => Right(Diamonds)
      case `s` => Right(Spades)
      case `c` => Right(Clubs)
      case  _ => Left(s"Error parsing '$str', is not a valid suite")
    }

  def abbreviate(suite:Suite) : String =
    suite match {
      case Hearts => h
      case Diamonds => d
      case Spades => s
      case Clubs => c
    }
}

object Rank {

  private val mappings = List(
    (Two, "2"), (Three, "3"), (Four, "4"), (Five, "5"), (Six, "6"), (Seven, "7"),
    (Eight, "8"), (Nine, "9"), (Ten, "T"), (Jack, "J"), (Queen, "Q"), (King, "K"), (Ace, "A")
  )

  def parse(str: String): Either[String, Rank] =
    mappings
      .find(_._2 == str)
      .map(_._1)
      .map(Right(_))
      .getOrElse(Left(s"Error parsing '$str', is not a valid rank"))

  def abbreviate(rank:Rank) : String =
    mappings
      .find(_._1 == rank)
      .map(_._2)
      .get // Rank is a sealed class, so it is totally safe call 'get' method here
}

object Card {

  def parse(str: String): Either[String, Card] =
    str.length match {
      case 2 => for {
        rank <- Rank parse str.charAt(0).toString
        suite <- Suite parse str.charAt(1).toString
      } yield Card(rank, suite)
      case _ => Left(s"Error parsing '$str', is not a valid card")
    }

  def abbreviate(card: Card): String = (Rank abbreviate card.rank) + (Suite abbreviate card.suite)

}



