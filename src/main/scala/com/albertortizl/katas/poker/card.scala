package com.albertortizl.katas.poker

sealed abstract class Suite
case object Hearts extends Suite //♥
case object Diamonds extends Suite //♦
case object Spades extends Suite //♠
case object Clubs extends Suite //♣

sealed abstract class Rank
case object Two extends Rank
case object Three extends Rank
case object Four extends Rank
case object Five extends Rank
case object Six extends Rank
case object Seven extends Rank
case object Eight extends Rank
case object Nine extends Rank
case object Ten extends Rank
case object Jack extends Rank
case object Queen extends Rank
case object King extends Rank
case object Ace extends Rank

case class Card(rank: Rank, suite: Suite)
