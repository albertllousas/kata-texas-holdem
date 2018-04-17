package com.albertortizl.katas.poker

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}


class ShowdownSpec extends FeatureSpec with GivenWhenThen with Matchers {


  val showdown = new Showdown()

  feature("Evaluate showdown") {

    info("As a texas hold'em live TV show announcer")
    info("I want to know what hands the players are holding and which player won")
    info("So that I don't have to spend time calculating each hand to know the winner")


    scenario("calculate showdown of folded hands") {

      Given("a round of folded poker players")
      val hands = List(
        "Kc 9s Ks Kd 9d 3c",
        "9c Ah Ks Kd 9d 3c",
        "Ac Qc Ks Kd 9d 3c"
      )

      When("showdown is evaluated")
      val result = showdown evaluate hands

      Then("hands should be not be ranked and winner not marked")
      result.isRight shouldBe true
      result.right.get should contain theSameElementsInOrderAs List(
        "Kc 9s Ks Kd 9d 3c",
        "9c Ah Ks Kd 9d 3c",
        "Ac Qc Ks Kd 9d 3c"
      )
    }

    scenario("calculate ranking and the winner of a poker hands") {

      Given("a round of poker players")
      val hands = List(
        "Kc 9s Ks Kd 9d 3c 6d",
        "9c Ah Ks Kd 9d 3c 6d",
        "Ac Qc Ks Kd 9d 3c",
        "9h 5s",
        "4d 2d Ks Kd 9d 3c 6d",
        "7s Ts Ks Kd 9d"
      )

      When("showdown is evaluated")
      val result = showdown evaluate hands

      Then("hands should be ranked and winner marked")
      result.isRight shouldBe true
      result.right.get should contain theSameElementsInOrderAs List(
        "Kc 9s Ks Kd 9d 3c 6d Full House (winner)",
        "9c Ah Ks Kd 9d 3c 6d Two Pair",
        "Ac Qc Ks Kd 9d 3c",
        "9h 5s",
        "4d 2d Ks Kd 9d 3c 6d Flush",
        "7s Ts Ks Kd 9d"
      )
    }


    scenario("calculate the winner using the kickers") {

      Given("a round of poker players with a couple of them with the same four of a kind")
      val hands = List(
        "9c Ah As Ad Ac 3c 6d",
        "Kc As As Ad Ac 3c 6d",
        "4c 7c 5s 7d 8d 3c 6d",
        "9h 5s"
      )

      When("showdown is evaluated")
      val result = showdown evaluate hands

      Then("hands should be ranked and winner marked")
      result.isRight shouldBe true
      result.right.get should contain theSameElementsInOrderAs List(
        "9c Ah As Ad Ac 3c 6d Four of a Kind",
        "Kc As As Ad Ac 3c 6d Four of a Kind (winner)",
        "4c 7c 5s 7d 8d 3c 6d Straight",
        "9h 5s"
      )
    }

    scenario("calculate the winners when there is a tie") {

      Given("a round of poker players with a couple of them with the straight")
      val hands = List(
        "2c 3d 4d 5d 6d Jc Qc",
        "2s 3d 4d 5d 6d Jc Qc"
      )

      When("showdown is evaluated")
      val result = showdown evaluate hands

      Then("the winners hands should be marked")
      result.isRight shouldBe true
      result.right.get should contain theSameElementsInOrderAs List(
        "2c 3d 4d 5d 6d Jc Qc Straight (winner)",
        "2s 3d 4d 5d 6d Jc Qc Straight (winner)"
      )
    }

    scenario("invalid input hand") {

      Given("an invalid round of poker players")
      val hands = List(
        "2c 3d 4d 5d 6d Jc Queen of Clubs",
        "2s 3d 4d 5d 6d Jc Qc"
      )

      When("showdown is evaluated")
      val result = showdown evaluate hands

      Then("an explanatory error message should be returned")
      result.isLeft shouldBe true
      result.left.get should equal ("Error parsing 'Queen', is not a valid card")

    }
  }

  feature("Rearrange the cards") {

    info("As a texas hold'em live TV show announcer")
    info("I want to see the cards arranged in each hand")
    info("So that I don't have to spend time thinking what cards and how are used in the hand")

    scenario("arranging the cards") {

      Given("a poker hands evaluator")
      When("evaluator is called with a round of hands")
      Then("the ranked cards are rearranged moving the cards used to the front of the line, sorted by face")

      pending
    }
  }


}
