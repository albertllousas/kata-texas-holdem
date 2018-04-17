package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class RankingTest extends FunSpec with Matchers {

  describe("evaluate ranking of a hand of five cards") {

    it("should rank a hand with a royal flush") {
      val fiveCards = List(Ace of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs)
      Ranking evaluate fiveCards should be(RoyalFlush)
    }

    it("should rank a hand with a straight flush") {
      val fiveCards = List(Seven of Clubs, Three of Clubs, Four of Clubs, Six of Clubs, Five of Clubs)
      Ranking evaluate fiveCards should be(StraightFlush)
    }

    it("should rank a hand with a four of a kind") {
      val fiveCards = List(Ace of Clubs, Three of Clubs, Ace of Hearts, Ace of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(FourOfAKind)
    }

    it("should rank a hand with a full house") {
      val fiveCards = List(Ace of Clubs, Ten of Clubs, Ten of Hearts, Ace of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(FullHouse)
    }

    it("should rank a hand with a flush") {
      val fiveCards = List(Seven of Spades, Five of Spades, Ace of Spades, Eight of Spades, King of Spades)
      Ranking evaluate fiveCards should be(Flush)
    }

    it("should rank a hand with a straight") {
      val fiveCards = List(Six of Clubs, Eight of Clubs, Nine of Hearts, Seven of Diamonds, Five of Spades)
      Ranking evaluate fiveCards should be(Straight)
    }

    it("should rank a hand with a three of a kind") {
      val fiveCards = List(Ten of Clubs, Three of Clubs, Ten of Hearts, Ten of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(ThreeOfAKind)
    }

    it("should rank a hand with a two pair") {
      val fiveCards = List(Ten of Clubs, Three of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(TwoPair)
    }

    it("should rank a hand with a one pair") {
      val fiveCards = List(Ten of Clubs, Six of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(OnePair)
    }

    it("should rank a hand with a high card") {
      val fiveCards = List(Ten of Clubs, Six of Clubs, Jack of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate fiveCards should be(HighCard)
    }

  }
  
  describe("choosing the best five-card combination") {

    implicit val ordering: Ordering[Hand] = HandComparator

    it("should choose the best combination of five cards when there are two possible full houses") {
      val sevenCards =
        List(Ten of Clubs, Ten of Diamonds, Ten of Hearts, Three of Diamonds, Ace of Spades, Ace of Clubs, Ace of Diamonds)

      Ranking bestHand sevenCards should be(
        Hand(List(Ten of Clubs, Ten of Diamonds, Ace of Spades, Ace of Clubs, Ace of Diamonds), FullHouse)
      )
    }

    it("should choose the best combination of five cards when there are two different possible straights") {
      val sevenCards =
        List(Six of Diamonds, Five of Hearts, Eight of Diamonds, Seven of Spades, Three of Spades, Four of Spades)

      Ranking bestHand sevenCards should be(
        Hand(List(Six of Diamonds, Five of Hearts, Eight of Diamonds, Seven of Spades, Four of Spades), Straight)
      )
    }

    it("should fail trying choose the best combination of five cards when there are less than five cards") {
      val cards = List(Ten of Clubs, Six of Clubs)
      val thrown = the[IllegalArgumentException] thrownBy Ranking.bestHand(cards)
      thrown.getMessage should equal(
        "requirement failed: Best hand on 'List(Card(Ten,Clubs), Card(Six,Clubs))' can not be calculated, at least 5 cards are required"
      )
    }
  }

}
