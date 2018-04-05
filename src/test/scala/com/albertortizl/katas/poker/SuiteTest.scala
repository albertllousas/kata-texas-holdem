package com.albertortizl.katas.poker

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class SuiteTest extends FunSpec with Matchers with TableDrivenPropertyChecks {

  val allSuites = List(Hearts, Diamonds, Spades, Clubs)

  val invalidStringSuite = Table("suite", "non valid", "D", "Diamond", "10", "♦")

  describe("string to suite parsing") {

    it("should parse all valid string ranks (abbreviations) to Suite") {
      val suites = List("h", "d", "s", "c").map(Suite.parse)
      all(suites) should be('right)
      suites.map(_.right.get) should contain theSameElementsAs allSuites
    }

    it("should non parse a non valid string abbreviation") {
      forAll(invalidStringSuite) { str =>
        Rank parse str should be('left)
      }
    }
  }
  describe("suite to string abbreviation") {

    it("should parse Suite to a string abbreviation") {
      val abbreviations = allSuites map Suite.abbreviation
      abbreviations should contain theSameElementsAs List("h", "d", "s", "c")
    }
  }

}
