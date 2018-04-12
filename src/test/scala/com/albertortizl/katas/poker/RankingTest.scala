package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class RankingTest extends FunSpec with Matchers {

  describe("evaluate ranking of a hand of five cards") {

    it("should rank a hand with a royal flush") {
      val hand = Hand(Ace of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs)
      Ranking evaluate hand should be (RoyalFlush)
    }

    it("should rank a hand with a straight flush") {
      val hand = Hand(Seven of Clubs, Three of Clubs, Four of Clubs, Six of Clubs, Five of Clubs)
      Ranking evaluate hand should be (StraightFlush)
    }

    it("should rank a hand with a four of a kind") {
      val hand = Hand(Ace of Clubs, Three of Clubs, Ace of Hearts, Ace of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (FourOfAKind)
    }

    it("should rank a hand with a full house") {
      val hand = Hand(Ace of Clubs, Ten of Clubs, Ten of Hearts, Ace of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (FullHouse)
    }

    it("should rank a hand with a flush") {
      val hand = Hand(Seven of Spades, Five of Spades, Ace of Spades, Eight of Spades, King of Spades)
      Ranking evaluate hand should be (Flush)
    }

    it("should rank a hand with a straight") {
      val hand = Hand(Six of Clubs, Eight of Clubs, Nine of Hearts, Seven of Diamonds, Five of Spades)
      Ranking evaluate hand should be (Straight)
    }

    it("should rank a hand with a three of a kind") {
      val hand = Hand(Ten of Clubs, Three of Clubs, Ten of Hearts, Ten of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (ThreeOfAKind)
    }

    it("should rank a hand with a two pair") {
      val hand = Hand(Ten of Clubs, Three of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (TwoPair)
    }

    it("should rank a hand with a one pair") {
      val hand = Hand(Ten of Clubs, Six of Clubs, Ten of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (OnePair)
    }

    it("should rank a hand with a high card") {
      val hand = Hand(Ten of Clubs, Six of Clubs, Jack of Hearts, Three of Diamonds, Ace of Spades)
      Ranking evaluate hand should be (HighCard)
    }

  }


//  tests score

  describe("choosing the best five-card combination") {

//    error cuando menos de tal

    it("should choose the best combination of five cards when you have two possible full houses") {
      val sevenCards =
        List(Ten of Clubs, Ten of Diamonds,Ten of Hearts, Three of Diamonds, Ace of Spades, Ace of Spades, Ace of Spades)

      Ranking bestFiveCardsCombination sevenCards should be (FourOfAKind, (Ten of Clubs, Ten of Diamonds, Ten of Hearts, Three of Diamonds, Ten of Spades))
    }

    it("should choose the best combination of five cards when you have three different possible straights") {
      val sevenCards =
        List(Ten of Clubs, Ten of Diamonds, Ten of Hearts, Three of Diamonds, Ace of Spades, Ace of Spades, Ace of Spades)

      Ranking bestFiveCardsCombination sevenCards should be (FourOfAKind, (Ten of Clubs, Ten of Diamonds, Ten of Hearts, Three of Diamonds, Ten of Spades))
    }
  }

}
