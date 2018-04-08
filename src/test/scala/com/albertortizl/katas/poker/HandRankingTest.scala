package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandRankingTest extends FunSpec with Matchers {

  describe("hand ranking") {

    it("should rank a hand with a royal flush") {
      val hand = Hand(List(Ace of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs))
      HandRanking evaluate hand should be (RoyalFlush)
    }

    it("should rank a hand with a straight flush") {
      val hand = Hand(List(Seven of Clubs, Three of Clubs, Four of Clubs, Six of Clubs, Five of Clubs))
      HandRanking evaluate hand should be (StraightFlush)
    }

    it("should rank a hand with a four of a kind") {
      val hand = Hand(List(Ace of Clubs, Three of Clubs, Ace of Hearts, Ace of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (FourOfAKind)
    }

    it("should rank a hand with a full house") {
      val hand = Hand(List(Ace of Clubs, Ten of Clubs, Ten of Hearts, Ace of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (FullHouse)
    }

    it("should rank a hand with a flush") {
      val hand = Hand(List(Seven of Spades, Five of Spades, Ace of Spades, Eight of Spades, King of Spades))
      HandRanking evaluate hand should be (Flush)
    }

    it("should rank a hand with a straight") {
      val hand = Hand(List(Six of Clubs, Eight of Clubs, Nine of Hearts, Seven of Diamonds, Five of Spades))
      HandRanking evaluate hand should be (Straight)
    }

    it("should rank a hand with a three of a kind") {
      val hand = Hand(List(Ten of Clubs, Three of Clubs, Ten of Hearts, Ten of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (ThreeOfAKind)
    }

    it("should rank a hand with a two pair") {
      val hand = Hand(List(Ten of Clubs, Three of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (TwoPair)
    }

    it("should rank a hand with a one pair") {
      val hand = Hand(List(Ten of Clubs, Six of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (OnePair)
    }

    it("should rank a hand with a high card") {
      val hand = Hand(List(Ten of Clubs, Six of Clubs, Jack of Hearts, Three of Diamonds, Ace of Spades))
      HandRanking evaluate hand should be (HighCard)
    }

    it("should fail trying to rank a hand with when there are not five cards") {
      val hand = Hand(List(Ten of Clubs, Six of Clubs))
      val thrown = the [IllegalArgumentException] thrownBy HandRanking.evaluate(hand)
      thrown.getMessage should equal(
        "requirement failed: 'Hand(List(Card(Ten,Clubs), Card(Six,Clubs)))' can not be ranked, must be exactly 5 cards"
      )
    }

  }

  describe("choosing the best five-card combination") {
    it("should choose the best combination of five cards in all possible combinations of five cards") {
      val hand = Hand(
        List(Ten of Clubs, Ten of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades, Ace of Spades, Ten of Hearts)
      )
      HandRanking bestFiveCardsCombination hand should be (FourOfAKind)
    }
  }

}
