package com.albertortizl.katas.poker

import com.albertortizl.katas.poker.implicits._

class Showdown(
                parseToPlayer: String => Either[String, Player] = Player.parse,
                parseToLine: HandState => String = HandState.toLine
              ) {

  private implicit val ordering: Ordering[Hand] = HandComparator
  private val bestHand: List[Card] => Hand = Ranking.bestHand
  private val isWinner: (Hand, List[Hand]) => Boolean = Hand.isWinner

  def evaluate(lines: List[String]): Either[String, List[String]] = {
    val players = lines.map(parseToPlayer).sequence
    val resultHands = players.map(compareHands)
    resultHands.map(_ map parseToLine)
  }

  private def compareHands(players: List[Player]): List[HandState] = {

    val hands: List[HandState] = players.map(
      player =>
        if (player.fold) Folded(player)
        else Finalist(player, bestHand(player.allCards))
    )

    hands.map {
      case finalist@Finalist(player, hand)
        if isWinner(finalist.bestHand, opponents(finalist, hands)) => Winner(player, hand)
      case nonWinner: Finalist => nonWinner
      case folded: Folded => folded
    }
  }

  private def opponents(finalist: Finalist, hands: List[HandState]): List[Hand] =
    hands.collect { case opponent: Finalist if opponent != finalist => opponent.bestHand }

}

