package com.albertortizl.katas.poker

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class CardTest extends FunSpec with Matchers with TableDrivenPropertyChecks {

  val validStringSuites = List("h", "d", "s", "c")

  val validStringRanks = List("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")

  val validStringCards = for (rank <- validStringRanks; suite <- validStringSuites) yield rank + suite

  val invalidStringCards = Table("rank", "ace of clubs", "10c", "TC" , "")

  describe("string to card parsing") {

    it("should parse all valid string cards to card") {
      val cards = validStringCards map Card.parse
      all(cards) should be('right)
    }

    it("should non parse a non valid string abbreviation") {
      forAll(invalidStringCards) { str =>
        Card parse str should be('left)
      }
    }
  }

  describe("abbreviate card") {

    it("should parse card to a string") {
      val abbreviations = Deck.cards.map(Card.abbreviate)
      abbreviations should contain theSameElementsInOrderAs validStringCards
    }
  }

}
