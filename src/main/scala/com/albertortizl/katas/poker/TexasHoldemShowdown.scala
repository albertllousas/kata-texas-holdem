package com.albertortizl.katas.poker

class TexasHoldemShowdown(parseInputLine: (String) => Hand = Hand.parse) {

  def evaluate(hands: List[String]): Either[String, List[String]] = {
    val showdown = hands.map(parseInputLine)
    Right(List(""))
  }

}
