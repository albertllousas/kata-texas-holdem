package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class PlayerTest extends FunSpec with Matchers {

  describe("parsing line to player round game") {

    it("should give an error parsing an empty line") {
      val player = Player parse ""
      player should be('left)
      player.left.get should equal ("Error parsing '', is not a valid card")
    }

    it("should give an error parsing a line with a single card") {
      val player = Player parse "Ks"
      player should be('left)
      player.left.get should equal ("'Ks' are not a valid set of cards")
    }

    it("should generate a game player from a line with two hole cards and  five community cards") {
      val player = Player parse "Ks Ah Th 2c 3c 3c 3c"
      player should be('right)
      player.right.get should have (
        'holeCards (HoleCards(King of Spades, Ace of Hearts)),
        'communityCards (List(Ten of Hearts, Two of Clubs, Three of Clubs, Three of Clubs, Three of Clubs)),
        'fold (false)
      )
    }

    it("should generate a fold game player from a line with two hole cards and less than five community cards") {
      val player = Player parse "Ks Ah Th 2c 3c"
      player should be('right)
      player.right.get should have (
        'holeCards (HoleCards(King of Spades, Ace of Hearts)),
        'communityCards (List(Ten of Hearts, Two of Clubs, Three of Clubs)),
        'fold (true)
      )
    }

    it("should give an error parsing a line with an invalid card pattern") {
      val player = Player parse "Ks ah Th 2c 3c"
      player should be('left)
      player.left.get should equal ("Error parsing 'a', is not a valid rank")
    }

  }

}
