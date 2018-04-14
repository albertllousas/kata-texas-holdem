package com.albertortizl.katas

import com.albertortizl.katas.poker.{FullHouse, StraightFlush, _}

/**
  * Follow comparators conventions:
  * A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
  */
object HandComparator extends Ordering[Hand] {
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
      case _ => {
        val comparison = strengthOfKind(a.cards) compare strengthOfKind(b.cards)
        if (comparison == 0) kickers(a.cards) compare kickers(b.cards) else comparison
      }
    }
  }

  private def kickers(cards: List[Card]): List[Int] = {
    val groupedRanks: Map[Rank, List[Card]] = cards.groupBy(_.rank)
    val onlyRanksWithoutRepetitions = groupedRanks.filter { case (_, v) => v.lengthCompare(1) == 0 }
    onlyRanksWithoutRepetitions.keys.map(_.value).toList
  }

  private implicit class KickersComparator(val kickers: List[Int]) extends AnyVal {
    def compare(l2: List[Int]): Int = {
      (kickers.sorted zip l2.sorted).map { case (a, b) => a compare b }.dropWhile(_ == 0).headOption.getOrElse(0)
    }
  }

  private def strengthOfKind(cards: List[Card]): Int = {
    val groupedRanks: Map[Rank, List[Card]] = cards.groupBy(_.rank)
    val deletedKickers = groupedRanks.filter { case (_, v) => v.lengthCompare(1) > 0 }
    deletedKickers.values.flatten.map(_.rank.value).sum
  }

  private def topCard(cards: List[Card]): Int = cards.maxBy(_.rank.value).rank.value
}

//http://www.scala-lang.org/api/2.12.0/scala/math/Ordering.html
//    https://www.adda52.com/poker/poker-rules/cash-game-rules/tie-breaker-rules


