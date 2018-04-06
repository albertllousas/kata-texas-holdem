package com.albertortizl.katas.poker

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}


class TexasHoldemShowdownSpec extends FeatureSpec with GivenWhenThen with Matchers{


  val texasHoldemShowdown = new TexasHoldemShowdown()

  feature("Evaluate showdown") {

    info("As a texas hold'em live TV show announcer")
    info("I want to know what hands the players are holding and which player won")
    info("So that I don't have to spend time calculating each hand to know the winner")

    scenario("calculate ranking of a poker hands") {

      Given("a hands of poker")
      val hands = List(
        "Kc 9s Ks Kd 9d 3c 6d",
        "9c Ah Ks Kd 9d 3c 6d",
        "Ac Qc Ks Kd 9d 3c",
        "9h 5s",
        "4d 2d Ks Kd 9d 3c 6d",
        "7s Ts Ks Kd 9d"
      )

      When("showdown is evaluated")
      val result = texasHoldemShowdown evaluate hands

      Then("hands with seven cards should be ranked")

//      result.isRight shouldBe (true)
    }

    scenario("calculate the winner") {

      Given("a poker hands evaluator")
      When("evaluator is called with a round of hands")
      Then("the winner hand should be marked")

      pending
    }
    scenario("calculate the winner using the kickers") {

      Given("a poker hands evaluator")
      When("evaluator is called with a round of hands with a couple of them with the same hand rank")
      Then("the winner hand should be marked")

      pending
    }

    scenario("calculate the winners when there is a draw") {

      Given("a poker hands evaluator")
      When("evaluator is called with a round of hands with a draw")
      Then("the winners hands should be marked")

      pending
    }

    scenario("invalid input hand") {

      Given("a poker hands evaluator")
      When("evaluator is called with a some invalid hands")
      Then("an explanatory error message should be returned")

      pending
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
