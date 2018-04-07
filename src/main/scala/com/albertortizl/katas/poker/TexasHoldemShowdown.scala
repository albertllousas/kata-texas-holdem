package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.EitherSequence._

class TexasHoldemShowdown(parseInputLine: (String) => Either[String,Hand] = Hand.parse) {

  def evaluate(hands: List[String]): Either[String, List[String]] = {

    val showdown: Either[String, List[Hand]] = hands.map(parseInputLine).sequence

    Right(List(""))
  }

}
