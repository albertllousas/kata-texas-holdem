package com.albertortizl.katas.poker

sealed abstract class HandRanking(val score: Int)
case object RoyalFlush extends HandRanking(10)
case object StraightFlush extends HandRanking(9)
case object FourOfAKind extends HandRanking(8)
case object FullHouse extends HandRanking(7)
case object Flush extends HandRanking(6)
case object Straight extends HandRanking(5)
case object ThreeOfAKind extends HandRanking(4)
case object TwoPair extends HandRanking(3)
case object OnePair extends HandRanking(2)
case object HighCard extends HandRanking(1)

object Ranking {

  val rankPriority = 100

  private def score(ranking: HandRanking, hand: Hand): Int = (ranking.score * rankPriority) + tiebreakerValue(hand)

  private def tiebreakerValue(hand: Hand): Int =
    hand.first.rank.value + hand.second.rank.value + hand.third.rank.value + hand.fourth.rank.value + hand.fifth.rank.value

  def bestFiveCardsCombination(cards: List[Card]): (HandRanking, Hand) = {

    require(cards.lengthCompare(5) >= 0, s"'$cards' can not be ranked, must 5 or more cards")

    cards
      .combinations(5)
      .map { case List(c1, c2, c3, c4, c5) => Hand(c1, c2, c3, c4, c5) }
      .map(hand => (evaluate(hand), hand))
      .maxBy(ranking => score(ranking._1, ranking._2))
  }

  def evaluate(hand: Hand): HandRanking = {

    if (hand.isSameSuit && hand.isConsecutive && hand.isHighCard(Ace)) RoyalFlush
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

  implicit class Rankable(val hand: Hand) extends AnyVal {

    def cards = hand.first :: hand.second :: hand.third :: hand.fourth :: hand.fifth :: Nil

    def isSameSuit: Boolean = cards.map(_.suite).toSet.size == 1

    def isConsecutive: Boolean = {
      val sortedCards = cards.sortWith(compareRank)
      sortedCards.last.rank.value - sortedCards.head.rank.value == sortedCards.length - 1
    }

    def isHighCard(rank: Rank): Boolean = cards.maxBy(_.rank.value).rank == rank

    def hasGroupOf(size: Int): Boolean = {
      rankRepetitions(cards).values.exists(_ == size)
    }

    def hasGroupsOf(firstGroupSize: Int)(secondGroupSize: Int): Boolean = {
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
