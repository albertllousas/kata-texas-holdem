package com.albertortizl.katas.poker

case class Hand(cards: List[Card])

//case class Ranked private (override val cards: List[Card]) extends Hand(cards)

object Hand {
  def parse(hand: String): Hand = {

    hand.split(" ").map(hand =>{
      hand
    })

    Hand(List())
  }
}

