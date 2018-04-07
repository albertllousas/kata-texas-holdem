package com.albertortizl.katas.poker

sealed abstract class HandRanking(val score: Int)
object RoyalFlush extends HandRanking(10)
object StraightFlush extends HandRanking(9)
object FourOfAKind extends HandRanking(8)
object FullHouse extends HandRanking(7)
object Flush extends HandRanking(6)
object Straight extends HandRanking(5)
object ThreeOfAKind extends HandRanking(4)
object TwoPair extends HandRanking(3)
object OnePair extends HandRanking(2)
object HighCard extends HandRanking(1)


object HandRanking {

  def bestFiveCardCombination(hand: Hand): HandRanking = {
    require(hand.cards.lengthCompare(7) == 0,
      s"Must be exactly 7 cards to find the best 5 cards combination for '$hand'")
    hand
      .cards
      .combinations(5)
      .map(Hand(_))
      .map(evaluate)
      .maxBy(_.score)
  }


  def evaluate(hand: Hand): HandRanking = {

    require(hand.cards.lengthCompare(5) == 0,s"'$hand' can not be ranked, must be exactly 5 cards")

    if (hand.isSameSuit && hand.isConsecutive && hand.hasHighCard(Ace)) RoyalFlush
    else if (hand.isSameSuit && hand.isConsecutive) StraightFlush
    else if (hand.hasGroupOf(4)) FourOfAKind
    else if (hand.hasGroupsOf(3)(2)) FullHouse
    else if (hand.isSameSuit) Flush
    else if (hand.isConsecutive) Straight
    else if (hand.hasGroupOf(3)) ThreeOfAKind
    else if (hand.hasGroupsOf(2)(2)) TwoPair
    else if (hand.hasGroupOf(2)) OnePair
    else HighCard
  }

  private val compareRank: (Card, Card) => Boolean = (c1, c2) => c1.rank.value < c2.rank.value

  implicit class RankableHand(val hand: Hand) extends AnyVal {

    def isSameSuit: Boolean = hand.cards.map(_.suite).toSet.size == 1

    def isConsecutive: Boolean = {
      val sortedCards = hand.cards.sortWith(compareRank)
      sortedCards.last.rank.value - sortedCards.head.rank.value == sortedCards.length - 1
    }

    def hasHighCard(rank: Rank): Boolean = hand.cards.maxBy(_.rank.value).rank == rank

    def hasGroupOf(size: Int): Boolean = groupByRank(hand.cards).values.exists(_.lengthCompare(size) == 0)

    def hasGroupsOf(firstGroupSize: Int)(secondGroupSize: Int): Boolean = {
      val firstGroupRank = groupByRank(hand.cards).mapValues(_.size).find(_._2 == firstGroupSize).map(_._1)
      firstGroupRank.exists(rank => {
        val cardsWithoutFirstGroup = hand.cards.filter(_.rank != rank)
        groupByRank(cardsWithoutFirstGroup).values.exists(_.lengthCompare(secondGroupSize) == 0)
      })
    }

    private def groupByRank(cards: List[Card]): Map[Rank, List[Rank]] = cards.map(_.rank).groupBy(identity)
  }

}




