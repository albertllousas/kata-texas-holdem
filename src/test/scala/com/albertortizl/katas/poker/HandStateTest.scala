package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandStateTest extends FunSpec with Matchers {

  describe("hand to string") {

    it("should parse winner hand to a string") {
      val player = Player(HoleCards(Two of Clubs, Four of Clubs), List(Jack of Clubs, Seven of Clubs, Ace of Clubs, King of Diamonds, Queen of Spades))
      val hand = Hand(List(Two of Clubs, Four of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs),Flush)
      val winner = Winner(player, hand)
      HandState toLine winner should equal ("2c 4c Jc 7c Ac Kd Qs Flush (winner)")
    }

    it("should parse finalist hand to a string") {
      val player = Player(HoleCards(Two of Clubs, Four of Clubs), List(Jack of Clubs, Seven of Clubs, Ace of Clubs, King of Diamonds, Queen of Spades))
      val hand = Hand(List(Two of Clubs, Four of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs),Flush)
      val winner = Finalist(player, hand)
      HandState toLine winner should equal ("2c 4c Jc 7c Ac Kd Qs Flush")
    }

    it("should parse folded hand to a string") {
      val player = Player(HoleCards(Two of Clubs, Four of Clubs), List(Ace of Clubs, King of Diamonds, Queen of Spades))
      val winner = Folded(player)
      HandState toLine winner should equal ("2c 4c Ac Kd Qs")
    }

  }
}
