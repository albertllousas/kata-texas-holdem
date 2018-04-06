package com.albertortizl.katas.poker

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class SuiteTest extends FunSpec with Matchers with TableDrivenPropertyChecks {

  val invalidStringSuite = Table("suite", "non valid", "D", "Diamond", "10", "♦")

  describe("string to suite parsing") {

    it("should parse all valid string suites to suite") {
      val suites = List("h", "d", "s", "c").map(Suite.parse)
      all(suites) should be('right)
      suites.map(_.right.get) should contain theSameElementsInOrderAs Deck.Suites
    }

    it("should non parse a non valid string") {
      forAll(invalidStringSuite) { str =>
        Rank parse str should be('left)
      }
    }
  }
  describe("abbreviate suite") {

    it("should abbreviate suite to a string") {
      val abbreviations = Deck.Suites map Suite.abbreviate
      abbreviations should contain theSameElementsInOrderAs List("h", "d", "s", "c")
    }
  }

}
