package ru.yandex.fs.example;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

class Attendee {

  record Slide() {}
  record Brain() {}

  static Brain learn(Brain b, Slide s) {
    return b;
  }

  static Flux<String> insight(Tuple2<Brain, Boolean> state) {
    return state.getT2() ? Flux.just("💡") : Flux.empty();
  }

  static Flux<String> attendee(Flux<Slide> slides, Flux<Boolean> rnd) {
    return slides
      .scan(new Brain(), (brain, slide) -> learn(brain, slide))
      .zipWith(rnd, 1)
      .concatMap(state -> insight(state));
  }

}
