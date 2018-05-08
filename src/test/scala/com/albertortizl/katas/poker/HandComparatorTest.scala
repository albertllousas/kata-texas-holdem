package com.albertortizl.katas.poker

import org.scalatest.{FunSpec, Matchers}

class HandComparatorTest extends FunSpec with Matchers {

  describe("comparing hands with different kind of ranking ") {

    it("if two players have different hands ranking, the strongest rank wins") {
      val fullHouse = Hand(List(Ten of Clubs, Ten of Diamonds, Ace of Spades, Ace of Clubs, Ace of Diamonds), FullHouse)
      val straight = Hand(List(Six of Diamonds, Five of Hearts, Eight of Diamonds, Seven of Spades, Four of Spades), Straight)
      HandComparator compare(fullHouse, straight) should be(1)
    }
  }

  describe("breaking a hand ranking tie") {

    it("if two players have Royal Flushes, they split the pot") {
      val hand1 = Hand(List(Ace of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs), RoyalFlush)
      val hand2 = Hand(List(Ace of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs), RoyalFlush)
      HandComparator compare(hand1, hand2) should be(0)
    }

    it("if two players have Straight flushes, the winner is the player with the highest card used in the Straight") {
      val hand1 = Hand(List(Nine of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, King of Clubs), StraightFlush)
      val hand2 = Hand(List(Nine of Clubs, Jack of Clubs, Ten of Clubs, Queen of Clubs, Eight of Clubs), StraightFlush)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have Four of a Kind, the winner is the player with the highest kicker") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Clubs, Nine of Clubs, Nine of Clubs, Seven of Clubs), FourOfAKind)
      val hand2 = Hand(List(Nine of Clubs, Nine of Clubs, Nine of Clubs, Nine of Clubs, Eight of Clubs), FourOfAKind)
      HandComparator compare(hand1, hand2) should be(-1)
    }

    it("if two players have Full House, the strength of the three of a kind to determine the winner") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Seven of Diamonds), FullHouse)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, Seven of Spades, Seven of Clubs, Seven of Diamonds), FullHouse)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have Flushes, compare highest card until break the tie") {
      val hand1 = Hand(List(Two of Clubs, Four of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs), Flush)
      val hand2 = Hand(List(Two of Clubs, Three of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs), Flush)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have the same Flush with different suite, they split the pot") {
      val hand1 = Hand(List(Two of Clubs, Four of Clubs, Jack of Clubs, Seven of Clubs, Ace of Clubs), Flush)
      val hand2 = Hand(List(Two of Diamonds, Four of Diamonds, Jack of Diamonds, Seven of Diamonds, Ace of Diamonds), Flush)
      HandComparator compare(hand1, hand2) should be(0)
    }

    it("if two players have Straights, one starting with ace, the other with two the winner is the second one") {
      val hand1 = Hand(List(Ace of Clubs, Two of Diamonds, Three of Clubs, Four of Clubs, Five of Clubs), Straight)
      val hand2 = Hand(List(Two of Diamonds, Three of Clubs, Four of Clubs, Five of Clubs, Six of Clubs), Straight)
      HandComparator compare(hand1, hand2) should be(-1)
    }

    it("if two players have Straights, the winner is the player with the highest card used in the Straight") {
      val hand1 = Hand(List(Nine of Clubs, Jack of Diamonds, Ten of Clubs, Queen of Clubs, King of Clubs), Straight)
      val hand2 = Hand(List(Nine of Clubs, Jack of Diamonds, Ten of Clubs, Queen of Clubs, Eight of Clubs), Straight)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have Straights, if both straights end in a card of the same strength, the hand is tied") {
      val hand1 = Hand(List(Nine of Clubs, Jack of Diamonds, Ten of Clubs, Queen of Clubs, King of Clubs), Straight)
      val hand2 = Hand(List(Nine of Clubs, Jack of Diamonds, Ten of Clubs, Queen of Clubs, King of Clubs), Straight)
      HandComparator compare(hand1, hand2) should be(0)
    }

    it("if two players hold three of a kind, the strength of the three of a kind determine the winner") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Eight of Diamonds), ThreeOfAKind)
      val hand2 = Hand(List(Seven of Clubs, Seven of Diamonds, Seven of Spades, Eight of Clubs, Nine of Diamonds), ThreeOfAKind)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have the same three of a kind, then kickers are used to determine the winner") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Eight of Diamonds), ThreeOfAKind)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Nine of Diamonds), ThreeOfAKind)
      HandComparator compare(hand1, hand2) should be(-1)
    }

    it("if two players have the same three of a kind and same kickers, then they split the pot") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Eight of Diamonds), ThreeOfAKind)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, Nine of Spades, Seven of Clubs, Eight of Diamonds), ThreeOfAKind)
      HandComparator compare(hand1, hand2) should be(0)
    }


    it("if two players hold two or single pair, the highest pair is used to determine the winner") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Seven of Spades, Seven of Clubs, Eight of Diamonds), TwoPair)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, Ten of Spades, Ten of Clubs, Eight of Diamonds), TwoPair)
      HandComparator compare(hand1, hand2) should be(-1)
    }

    it("if two players have the same two or single pair, then kickers are used to determine the winner") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Ace of Spades, Seven of Clubs, Ten of Diamonds), OnePair)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, King of Spades, Seven of Clubs, Eight of Diamonds), OnePair)
      HandComparator compare(hand1, hand2) should be(1)
    }

    it("if two players have the same two or single pair and same kickers, then they split the pot") {
      val hand1 = Hand(List(Nine of Clubs, Nine of Diamonds, Ten of Spades, Ten of Clubs, Eight of Diamonds), TwoPair)
      val hand2 = Hand(List(Nine of Clubs, Nine of Diamonds, Ten of Spades, Ten of Clubs, Eight of Diamonds), TwoPair)
      HandComparator compare(hand1, hand2) should be(0)
    }

    it("when no player has even a pair, then the highest card wins") {
      val hand1 = Hand(List(Nine of Clubs, Two of Diamonds, Ten of Spades, Jack of Clubs, Queen of Diamonds), HighCard)
      val hand2 = Hand(List(Nine of Clubs, Ace of Diamonds, Ten of Spades, Jack of Clubs, Queen of Diamonds), HighCard)
      HandComparator compare(hand1, hand2) should be(-1)
    }

  }
}
