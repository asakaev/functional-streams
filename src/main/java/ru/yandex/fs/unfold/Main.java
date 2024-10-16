package ru.yandex.fs.unfold;

import reactor.core.publisher.Flux;

import static ru.yandex.fs.unfold.Unfold.*;

public class Main {

  public static void main(String[] args) {
    Flux
      .just(evens(), fib(), countdown(10))
      .concatMap(xs ->
        xs.take(8).collectList().flux()
      )
      .doOnNext(xs -> System.out.println(xs))
      .then()
      .block();
  }

}
