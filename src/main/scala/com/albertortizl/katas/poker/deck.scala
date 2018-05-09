package com.albertortizl.katas.poker

sealed abstract class Suite
case object Hearts extends Suite   //♥
case object Diamonds extends Suite //♦
case object Spades extends Suite   //♠
case object Clubs extends Suite    //♣

sealed abstract class Rank(val maxValue:Int) {
  def of(suite:Suite) :Card = Card(this, suite)
}
case object Two extends Rank(2)
case object Three extends Rank(3)
case object Four extends Rank(4)
case object Five extends Rank(5)
case object Six extends Rank(6)
case object Seven extends Rank(7)
case object Eight extends Rank(8)
case object Nine extends Rank(9)
case object Ten extends Rank(10)
case object Jack extends Rank(11)
case object Queen extends Rank(12)
case object King extends Rank(13)
case object Ace extends Rank(14)

case class Card(rank: Rank, suite: Suite)

trait DefaultParsing[A] {
  def parse(abbreviation: String): Either[String, A]
  def abbreviate(element:A) : String
}

object Deck {
  val Suites: List[Suite] = List(Hearts, Diamonds, Spades, Clubs)
  val Ranks: List[Rank] = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val Cards: List[Card] = for (rank <- Ranks; suite <- Suites) yield Card(rank, suite)
}

object Suite extends DefaultParsing[Suite]{


  def parse(str: String): Either[String, Suite] =
    str match {
      case "h" => Right(Hearts)
      case "d" => Right(Diamonds)
      case "s" => Right(Spades)
      case "c" => Right(Clubs)
      case  _ => Left(s"Error parsing '$str', is not a valid suite")
    }

  def abbreviate(suite:Suite) : String =
    suite match {
      case Hearts => "h"
      case Diamonds => "d"
      case Spades => "s"
      case Clubs => "c"
    }
}

object Rank extends DefaultParsing[Rank]{

  def parse(str: String): Either[String, Rank] =
    str match {
      case "2" => Right(Two)
      case "3" => Right(Three)
      case "4" => Right(Four)
      case "5" => Right(Five)
      case "6" => Right(Six)
      case "7" => Right(Seven)
      case "8" => Right(Eight)
      case "9" => Right(Nine)
      case "T" => Right(Ten)
      case "J" => Right(Jack)
      case "Q" => Right(Queen)
      case "K" => Right(King)
      case "A" => Right(Ace)
      case _ => Left(s"Error parsing '$str', is not a valid rank")
    }

  def abbreviate(rank:Rank) : String =
    rank match {
      case Ten => "T"
      case Jack => "J"
      case Queen => "Q"
      case King => "K"
      case Ace => "A"
      case rank:Rank => rank.maxValue.toString
    }
}

object Card extends DefaultParsing[Card] {

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