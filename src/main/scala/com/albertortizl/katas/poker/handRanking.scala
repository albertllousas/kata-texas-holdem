package com.albertortizl.katas.poker

sealed abstract class HandRanking
object RoyalFlush extends HandRanking
object StraightFlush extends HandRanking
object FourOfAKind extends HandRanking
object FullHouse extends HandRanking
object Flush extends HandRanking
object Straight extends HandRanking
object ThreeOfAKind extends HandRanking
object TwoPair extends HandRanking
object OnePair extends HandRanking
object HighCard extends HandRanking


object HandRanking {

  def evaluate(hand: Hand): HandRanking = {
    if (hand.isSameSuit && hand.isConsecutive && hand.hasHighCard(Ace)) RoyalFlush
    else if (hand.isSameSuit && hand.isConsecutive) StraightFlush
    else if (hand.hasGroupOf(4)) FourOfAKind
    else HighCard
  }

  implicit class RankableHand(val hand: Hand) extends AnyVal {
    def isSameSuit: Boolean = hand.cards.map(_.suite).toSet.size == 1
    def isConsecutive: Boolean = {
      val sortedCards = hand.cards.sortWith((c1,c2)=> c1.rank.value < c2.rank.value)
      sortedCards.last.rank.value - sortedCards.head.rank.value == sortedCards.length -1
    }
    def hasHighCard(rank:Rank):Boolean = hand.cards.maxBy(_.rank.value).rank == rank
    def hasGroupOf(size:Int):Boolean = hand.cards.map(_.rank).groupBy(identity).values.exists(_.lengthCompare(size) == 0)
  }

}
//  private def fff(implicit cards:List[(Rank,Suite)]):Boolean = true
//
//  private def containsGroupOfSize hasGroupOf[A](repetitions:Int)(elements: A*): Boolean =
//    elements.groupBy(identity).values.exists(group => group.lengthCompare(repetitions) == 0)
//
//  private def isConsecutive(r1: Rank, r2: Rank, r3: Rank, r4: Rank, r5: Rank): Boolean =
//    rankIndex(r5) - rankIndex(r1) == 4



