package ru.yandex.fs.weather;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Instant;
import java.util.function.Function;

class LogSimulator {

  static Log<Tuple2<Integer, Instant>> make() {
    return new Log<>() {
      public Boolean produce(Tuple2<Integer, Instant> t) {
        System.out.println("  produce " + t);
        return true;
      }
    };
  }

  static <T> Flux<T> use(Function<Log<Tuple2<Integer, Instant>>, Flux<T>> f) {
    return Flux.using(
      () -> {
        System.out.println("Entering the Log scope");
        return make();
      },
      f,
      log -> System.out.println("Leaving the Log scope")
    );
  }

}
