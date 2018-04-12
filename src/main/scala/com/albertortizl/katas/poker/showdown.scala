package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.ShowdownStages.{initialState, parseFolds,determineWinners, rank}
import com.albertortizl.katas.poker.implicits._


sealed trait ShowdownOutcome
case class Fold(playerCards:Folded) extends ShowdownOutcome
case class Win(playerCards:Finalist) extends ShowdownOutcome
case class Loss(playerCards:Finalist) extends ShowdownOutcome


class Showdown(parseInputLine: (String) => Either[String, PlayerCards] = PlayerCards.parse) {

  def evaluate(lines: List[String]): Either[String, List[String]] = {

    lines
      .map(parseInputLine)
      .sequence
      .map(initialState(_))
      .map(parseFolds(_))
      .map(rank(_))
      .map(determineWinners(_))
    //      .map(Hand.toLine)

    Right(List(""))
  }

}


object ShowdownStages {

  def initialState(playerCards: List[PlayerCards]):List[Alive] = List()

  def parseFolds(states: List[HandState]):List[HandState] = List()

  def rank(
            states: List[HandState],
            bestFiveCardsCombination: (List[Card]) => (HandRanking, Hand) = Ranking.bestFiveCardsCombination
          ): List[Finalist] = {
//    hands
//      .map(hand => if (hand.communityCards.lengthCompare(5) < 0) hand.fold() else hand)
//      .map {
//        case h: Folded => h
//        case h: Hand =>
//          val ranking = bestFiveCardsCombination(h)
//          h.rankWith(ranking._1, ranking._2)
    List()
      }

  def determineWinners(hands: List[Finalist], score: (HandRanking,Hand)=> Int = Ranking.score ): List[ShowdownOutcome] = {
    List()
  }

}
