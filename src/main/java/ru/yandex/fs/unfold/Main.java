package ru.yandex.fs.unfold;

import reactor.core.publisher.Flux;

import static ru.yandex.fs.unfold.Unfold.*;

public class Main {

  public static void main(String[] args) {
    Flux
      .just(
        evens(),
        fib(),
        factorial(),
        collatz(3),
        constant("✨"),
        alternate(),
        powersOfTen(),
        countdown(10),
        digits(12345),
        repeatN(2, "👋"),
        random(0L)
      )
      .concatMap(xs ->
        xs.take(8).collectList().flux()
      )
      .doOnNext(xs -> System.out.println(xs))
      .then()
      .block();
  }

}
