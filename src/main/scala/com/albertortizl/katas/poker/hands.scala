package com.albertortizl.katas.poker


import scala.util.Sorting

case class Hand(cards: List[Card], ranking: HandRanking)

sealed trait HandState
case class Folded(player: Player) extends HandState
case class Finalist(player: Player, bestHand: Hand) extends HandState
case class Winner(player: Player, bestHand: Hand) extends HandState

object HandState {

  def toLine(handState:HandState):String =
    handState match {
      case w:Winner => s"${Player.abbreviate(w.player)} ${w.bestHand.ranking.name} (winner)"
      case f:Finalist => s"${Player.abbreviate(f.player)} ${f.bestHand.ranking.name}"
      case f:Folded => s"${Player.abbreviate(f.player)}"
    }
}

object Hand {

  private val Win = true
  private val Loss = false
  val FirstIsLess: Int = -1
  val BothAreEqual: Int = 0
  val FirstIsGreater: Int = 1

  def isWinner(hand: Hand, opponents: List[Hand])(implicit ordering:Ordering[Hand]):  Boolean = {

    val opponentHands = opponents.toArray
    Sorting.quickSort(opponentHands)(ordering)
    val opponentsHandsDesc: Array[Hand] = opponentHands.reverse

    val compareResult = opponentsHandsDesc
      .headOption
      .map(opponentsBestHand => ordering.compare(hand, opponentsBestHand))
      .getOrElse(FirstIsGreater)

    compareResult match {
      case FirstIsGreater | BothAreEqual => Win
      case FirstIsLess => Loss
    }
  }
}

object HandComparator extends Ordering[Hand] {


  /**
    * Following comparators conventions:
    * A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
    */
  def compare(a: Hand, b: Hand): Int = {
    (a.ranking, b.ranking) match {
      case (rankingA, rankingB) if rankingA != rankingB => rankingA.value compare rankingB.value
      case _ => compareTie(a, b)
    }
  }

  private def compareTie(a: Hand, b: Hand): Int = {
    a.ranking match {
      case RoyalFlush => 0
      case StraightFlush | Straight => topCard(a.cards) compare topCard(b.cards)
      case FullHouse => strengthOfKind(a.cards) compare strengthOfKind(b.cards)
      case _ =>
        val comparison = strengthOfKind(a.cards) compare strengthOfKind(b.cards)
        if (comparison == 0) kickers(a.cards) compare kickers(b.cards) else comparison
    }
  }

  private def kickers(cards: List[Card]): List[Int] = {
    val groupedRanks: Map[Rank, List[Card]] = cards.groupBy(_.rank)
    val onlyRanksWithoutRepetitions = groupedRanks.filter { case (_, v) => v.lengthCompare(1) == 0 }
    onlyRanksWithoutRepetitions.keys.map(_.value).toList
  }

  private implicit class KickersComparator(val kickers: List[Int]) extends AnyVal {
    def compare(to: List[Int]): Int = {
      (kickers.sorted zip to.sorted).map { case (a, b) => a compare b }.dropWhile(_ == 0).headOption.getOrElse(0)
    }
  }

  private def strengthOfKind(cards: List[Card]): Int = {
    val groupedRanks: Map[Rank, List[Card]] = cards.groupBy(_.rank)
    val deletedKickers = groupedRanks.filter { case (_, v) => v.lengthCompare(1) > 0 }
    deletedKickers.values.flatten.map(_.rank.value).sum
  }

  private def topCard(cards: List[Card]): Int = cards.maxBy(_.rank.value).rank.value
}


