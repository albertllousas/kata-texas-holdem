package com.albertortizl.katas.poker

sealed abstract class HandRanking(val value: Int, val name:String)
case object RoyalFlush extends HandRanking(10, "Royal Flush")
case object StraightFlush extends HandRanking(9, "Straight Flush")
case object FourOfAKind extends HandRanking(8, "Four of a Kind")
case object FullHouse extends HandRanking(7, "Full House")
case object Flush extends HandRanking(6, "Flush")
case object Straight extends HandRanking(5, "Straight")
case object ThreeOfAKind extends HandRanking(4, "Three of a Kind")
case object TwoPair extends HandRanking(3, "Two Pair")
case object OnePair extends HandRanking(2, "One Pair")
case object HighCard extends HandRanking(1, "High Card")


object Ranking {

  private val compareRank: (Card, Card) => Boolean = (c1, c2) => c1.rank.value < c2.rank.value

  def bestHand(cards: List[Card])(implicit ordering: Ordering[Hand]): Hand = {

    require(cards.lengthCompare(5) >= 0, s"Best hand on '$cards' can not be calculated, at least 5 cards are required")

    cards
      .combinations(5)
      .map(fiveCards => Hand(fiveCards, evaluate(fiveCards)))
      .max(ordering)
  }

  def evaluate(cards: List[Card]): HandRanking = {

      require(cards.lengthCompare(5) == 0, s"'$cards' can not be evaluated, should be 5 cards")

      if (cards.haveSameSuit && cards.areConsecutive && cards.haveHighCard(Ace)) RoyalFlush
      else if (cards.haveSameSuit && cards.areConsecutive) StraightFlush
      else if (cards.haveGroupOf(4)) FourOfAKind
      else if (cards.haveGroupsOf(3)(2)) FullHouse
      else if (cards.haveSameSuit) Flush
      else if (cards.areConsecutive) Straight
      else if (cards.haveGroupOf(3)) ThreeOfAKind
      else if (cards.haveGroupsOf(2)(2)) TwoPair
      else if (cards.haveGroupOf(2)) OnePair
      else HighCard

  }


  private implicit class Rankable(val cards: List[Card]) extends AnyVal {

    def haveSameSuit: Boolean = cards.map(_.suite).toSet.size == 1

    def areConsecutive: Boolean = {
      val sortedCards = cards.sortWith(compareRank)
      sortedCards.last.rank.value - sortedCards.head.rank.value == sortedCards.length - 1
    }

    def haveHighCard(rank: Rank): Boolean = cards.maxBy(_.rank.value).rank == rank

    def haveGroupOf(size: Int): Boolean = {
      rankRepetitions(cards).values.exists(_ == size)
    }

    def haveGroupsOf(firstGroupSize: Int)(secondGroupSize: Int): Boolean = {
      val firstGroupRank = rankRepetitions(cards).find(_._2 == firstGroupSize).map(_._1)
      firstGroupRank.exists(rank => {
        val cardsWithoutFirstGroup = cards.filter(_.rank != rank)
        rankRepetitions(cardsWithoutFirstGroup).values.exists(_ == secondGroupSize)
      })
    }

    private def rankRepetitions(cards: List[Card]): Map[Rank, Int] =
      cards.map(_.rank).groupBy(identity).mapValues(_.size)
  }

}
