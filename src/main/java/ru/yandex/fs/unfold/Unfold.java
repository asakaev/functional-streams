package ru.yandex.fs.unfold;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;
import ru.yandex.fs.flux.FluxOps;

import java.util.Optional;

class Unfold {

  static Flux<Integer> evens() {
    return FluxOps.unfold(0, s -> Optional.of(Tuples.of(s, s + 2)));
  }

  static Flux<Integer> fib() {
    return FluxOps.unfold(Tuples.of(0, 1), s -> {
      var a = s.getT1();
      var b = s.getT2();
      return Optional.of(Tuples.of(a, Tuples.of(b, a + b)));
    });
  }

  static Flux<Integer> countdown(Integer n) {
    return FluxOps.unfold(n, s ->
      s == 0 ? Optional.empty() : Optional.of(Tuples.of(s, s - 1))
    );
  }

}
