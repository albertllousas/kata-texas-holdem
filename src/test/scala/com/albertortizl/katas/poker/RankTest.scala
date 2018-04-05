package com.albertortizl.katas.poker

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class RankTest extends FunSpec with Matchers with TableDrivenPropertyChecks {

  val allRanks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  val validStringRanks = List("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")

  val invalidStringRanks = Table("rank", "non valid", "10", "t", "a", "33", "")

  describe("string to rank parsing") {

    it("should parse all valid string ranks (abbreviations) to Rank") {
      val ranks = validStringRanks.map(Rank.parse)
      all(ranks) should be('right)
      ranks.map(_.right.get) should contain theSameElementsAs allRanks
    }

    it("should non parse a non valid string abbreviation") {
      forAll(invalidStringRanks) { str =>
        Rank parse str should be('left)
      }
    }
  }
  describe("rank to string abbreviation") {

    it("should parse Rank to a string abbreviation") {
      val abbreviations = allRanks.map(Rank.abbreviation)
      abbreviations should contain theSameElementsAs validStringRanks
    }
  }

}