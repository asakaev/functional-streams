package ru.yandex.fs.example;

import reactor.core.publisher.Flux;

class Pipe {

  static Flux<String> pipe(Flux<Integer> xs) {
    return xs
      .scan(0, (acc, x) -> acc + x)
      .skip(1)
      .concatMap(n ->
        Flux.just(n.toString(), String.valueOf(n % 2 == 0))
      );
  }

}
