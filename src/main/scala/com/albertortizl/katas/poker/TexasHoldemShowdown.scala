package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.EitherSequence._

class TexasHoldemShowdown(parseInputLine: (String) => Either[String,Hand] = Hand.parse) {

  def evaluate(lines: List[String]): Either[String, List[String]] = {

    lines
      .map(parseInputLine)
      .sequence
      .right
      .map {
        hands =>
          val handRankings = hands.map(HandRanking.bestFiveCardsCombination)
          handRankings
      }

    Right(List(""))
  }

}
