package com.albertortizl.katas.poker

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class RankTest extends FunSpec with Matchers with TableDrivenPropertyChecks {

  val validStringRanks = List("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")

  val invalidStringRanks = Table("rank", "non valid", "10", "t", "a", "33", "")

  describe("string to rank parsing") {

    it("should parse all valid string ranks to rank") {
      val ranks = validStringRanks.map(Rank.parse)
      all(ranks) should be('right)
      ranks.map(_.right.get) should contain theSameElementsInOrderAs Deck.ranks
    }

    it("should non parse a non valid string") {
      forAll(invalidStringRanks) { str =>
        Rank parse str should be('left)
      }
    }
  }
  describe("abbreviate rank") {

    it("should abbreviate rank to a string") {
      val abbreviations = Deck.ranks.map(Rank.abbreviate)
      abbreviations should contain theSameElementsInOrderAs validStringRanks
    }
  }

}