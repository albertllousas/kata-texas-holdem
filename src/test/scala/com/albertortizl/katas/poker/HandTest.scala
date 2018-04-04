package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandTest extends FunSpec with Matchers{

  describe("'String' to 'Hand' parsing") {

    it("should generate a hand with zero cards from an empty string") {
      val hand = Hand parse ""
      hand.cards should have size 0
    }

    it("should generate a hand with one cards from a single card as string") {
      val hand = Hand parse "Ks"
      hand.cards should have size 1
    }

//    it("should produce NoSuchElementException when head is invoked") {
//      assertThrows[NoSuchElementException] {
//        Set.empty.head
//      }
//    }
  }

}
