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


  }

}
