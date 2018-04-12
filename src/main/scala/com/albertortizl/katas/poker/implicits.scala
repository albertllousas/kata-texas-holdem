package com.albertortizl.katas.poker

object implicits {

  implicit class SequenceOfEither[A, B](val seq: List[Either[B, A]]) extends AnyVal {

    def sequence: Either[B, List[A]] =
      seq.foldRight(Right(Nil): Either[B, List[A]]) {
        (either, acc) => {
          either.right.flatMap(element => acc.right.map(elements => element :: elements))
        }
      }
  }

}

