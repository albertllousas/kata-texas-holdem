package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.ShowdownStages.{determineWinners, evaluateRankings, parseFolds}
import com.albertortizl.katas.poker.implicits._

class Showdown(parseInputLine: (String) => Either[String, PlayerCards] = PlayerCards.parse,
               toLine: (HandState) => String = HandState.toLine) {

  def evaluate(lines: List[String]): Either[String, List[String]] = {

    lines
      .map(parseInputLine)
      .sequence
      .map(_ map Alive)
      .map(parseFolds)
      .map(evaluateRankings(_))
      .map(determineWinners(_))
      .map(_ map toLine)
  }

}

object ShowdownStages {

  def parseFolds(states: List[HandState]): List[HandState] =
    states.map {
      case Alive(pc@PlayerCards(_, communityCards)) if communityCards.lengthCompare(5) < 0 => Folded(pc)
      case state => state
    }

  def evaluateRankings(
                        states: List[HandState],
                        bestFiveCardsCombination: (List[Card]) => (HandRanking, Hand) = Ranking.bestFiveCardsCombination
                      ): List[HandState] =
    states.map {
      case Alive(pc@PlayerCards(HoleCards(p1, p2), communityCards)) =>
        val (handRanking, hand) = bestFiveCardsCombination(p1 :: p2 :: communityCards)
        Finalist(pc, handRanking, hand)
      case state => state
    }

  def determineWinners(states: List[HandState], score: (HandRanking, Hand) => Int = Ranking.score): List[HandState] = {
    List()
  }

}
