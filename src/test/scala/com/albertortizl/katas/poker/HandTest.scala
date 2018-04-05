package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandTest extends FunSpec with Matchers {

  describe("line to hand parsing") {

    it("should give an error parsing an empty line") {
      val hand = Hand parse ""
      hand should be('left)
      hand.left.get should equal ("Error parsing '', is not a valid card")
    }

    it("should generate a hand with one card from a line with a single card") {
      val hand = Hand parse "Ks"
      hand should be('right)
      hand.right.get.cards should have size 1
      hand.right.get.cards.head should be (King of Spades)
    }

    it("should generate a hand from a line with cards") {
      val hand = Hand parse "Ks Ah Th 2c 3c"
      hand should be('right)
      hand.right.get.cards should have size 5
      hand.right.get.cards should contain theSameElementsInOrderAs List(
        King of Spades, Ace of Hearts, Ten of Hearts, Two of Clubs, Three of Clubs
      )
    }

    it("should give an error parsing a line with an invalid card pattern") {
      val hand = Hand parse "Ks ah Th 2c 3c"
      hand should be('left)
      hand.left.get should equal ("Error parsing 'a', is not a valid rank")
    }

  }

}
