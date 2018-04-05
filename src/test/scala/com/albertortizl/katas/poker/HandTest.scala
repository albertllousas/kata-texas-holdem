package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandTest extends FunSpec with Matchers {

  describe("'String' to 'Hand' parsing") {

    it("should give an error parsing an empty string") {
      val hand = Hand parse ""
      hand should be('left)
      hand.left.get should equal ("Error parsing '', is not a valid card")
    }

    it("should generate a hand with one cards from a single card as string") {
      val hand = Hand parse "Ks"
      hand should be('right)
      hand.right.get.cards should have size 1
      hand.right.get.cards.head should be (Card(King, Spades))
    }

    //    it("should produce NoSuchElementException when head is invoked") {
    //      assertThrows[NoSuchElementException] {
    //        Set.empty.head
    //      }
    //    }
  }

}
