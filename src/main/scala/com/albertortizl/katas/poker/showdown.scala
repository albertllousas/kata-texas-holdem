package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.ShowdownStages.{determineWinners, parseFolds}
import com.albertortizl.katas.poker.implicits._

class Showdown(parseInputLine: (String) => Either[String, PlayerCards] = PlayerCards.parse,
               toLine: (HandState) => String = HandState.toLine) {

  private val evaluateRankings: List[HandState] => List[HandState] = ShowdownStages.evaluateRankings(_, Ranking.bestHand)

  def evaluate(lines: List[String]): Either[String, List[String]] = {

    lines
      .map(parseInputLine)
      .sequence
      .map(_ map Alive)
      .map(parseFolds)
      .map(evaluateRankings)
      .map(determineWinners)
      .map(_ map toLine)
  }

}

object ShowdownStages {

  def parseFolds(states: List[HandState]): List[HandState] =
    states.map {
      case Alive(pc@PlayerCards(_, communityCards)) if communityCards.lengthCompare(5) < 0 => Folded(pc)
      case state => state
    }

  def evaluateRankings(states: List[HandState], bestHand: (List[Card]) => Hand): List[HandState] =
    states.map {
      case Alive(pc@PlayerCards(HoleCards(p1, p2), communityCards)) =>
        Finalist(pc, bestHand(p1 :: p2 :: communityCards))
      case state => state
    }

  def determineWinners(states: List[HandState]): List[HandState] = {
    List()
  }

}
