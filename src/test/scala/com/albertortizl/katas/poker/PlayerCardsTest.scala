package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class PlayerCardsTest extends FunSpec with Matchers {

  describe("parsing line to player cards game") {

    it("should give an error parsing an empty line") {
      val playerCards = PlayerCards parse ""
      playerCards should be('left)
      playerCards.left.get should equal ("Error parsing '', is not a valid card")
    }

    it("should give an error parsing a line with a single card") {
      val playerCards = PlayerCards parse "Ks"
      playerCards should be('left)
      playerCards.left.get should equal ("'Ks' are not a valid set of cards")
    }

    it("should generate a game player cards from a line with cards") {
      val playerCards = PlayerCards parse "Ks Ah Th 2c 3c"
      playerCards should be('right)
      playerCards.right.get should have (
        'holeCards (HoleCards(King of Spades, Ace of Hearts)),
        'communityCards (List(Ten of Hearts, Two of Clubs, Three of Clubs))
      )
    }

    it("should give an error parsing a line with an invalid card pattern") {
      val playerCards = PlayerCards parse "Ks ah Th 2c 3c"
      playerCards should be('left)
      playerCards.left.get should equal ("Error parsing 'a', is not a valid rank")
    }

  }

}
