package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.Hand.isWinner
import org.scalatest.{FunSpec, Matchers}

class HandTest extends FunSpec with Matchers {

  implicit val ordering: Ordering[Hand] = HandComparator

  describe("determine if a hand is a winner") {

    it("should win if there is no opponents") {
      val hand = Hand(List(), FullHouse)
      isWinner(hand, List()) should be(true)
    }

    it("should win if hand is greater than any other hand") {
      val hand = Hand(List(), FullHouse)
      val opponents = List(Hand(List(), Straight), Hand(List(), OnePair), Hand(List(), TwoPair))
      isWinner(hand, opponents) should be(true)
    }

    it("should loss if hand is less than other hand") {
      val hand = Hand(List(), FullHouse)
      val opponents = List(Hand(List(), Straight), Hand(List(), OnePair), Hand(List(), FourOfAKind))
      isWinner(hand, opponents) should be(false)
    }

    it("should win if hand is equal to the highest hand of the opponents") {
      val hand = Hand(List(), RoyalFlush)
      val opponents = List(Hand(List(), Straight), Hand(List(), RoyalFlush), Hand(List(), FourOfAKind))
      isWinner(hand, opponents) should be(true)
    }

    it("should loss if there is a tie and the hand loss") {
      val hand = Hand(List(Two of Clubs, Three of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs), Flush)
      val opponents = List(Hand(List(Two of Clubs, Four of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs), Flush))
      isWinner(hand, opponents) should be(false)
    }

  }
}