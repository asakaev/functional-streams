package ru.yandex.fs.unfold;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;
import ru.yandex.fs.flux.FluxOps;

import java.util.Optional;
import java.util.Random;

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

  static Flux<Integer> factorial() {
    return FluxOps.unfold(Tuples.of(1, 1), s -> {
      var n = s.getT1();
      var acc = s.getT2();
      return Optional.of(Tuples.of(acc, Tuples.of(n + 1, acc * (n + 1))));
    });
  }

  static Flux<Integer> collatz(Integer num) {
    return FluxOps.unfold(num, n ->
      n == 1 ?
        Optional.empty() :
        Optional.of(Tuples.of(n, n % 2 == 0 ? n / 2 : 3 * n + 1)));
  }

  static <T> Flux<T> constant(T x) {
    return FluxOps.unfold(0, s -> Optional.of(Tuples.of(x, s)));
  }

  static Flux<Boolean> alternate() {
    return FluxOps.unfold(true, x ->
      Optional.of(Tuples.of(x, !x))
    );
  }

  static Flux<Integer> powersOfTen() {
    return FluxOps.unfold(1, x ->
      Optional.of(Tuples.of(x, x * 10))
    );
  }

  static Flux<Integer> countdown(Integer n) {
    return FluxOps.unfold(n, s ->
      s == 0 ? Optional.empty() : Optional.of(Tuples.of(s, s - 1))
    );
  }

  static Flux<Integer> digits(Integer n) {
    return FluxOps.unfold(n, x ->
      x == 0 ?
        Optional.empty() :
        Optional.of(Tuples.of(x % 10, x / 10))
    );
  }

  static <T> Flux<T> repeatN(Integer n, T x) {
    return FluxOps.unfold(n, i ->
      i == 0 ?
        Optional.empty() :
        Optional.of(Tuples.of(x, i - 1))
    );
  }

  static Flux<Integer> random(Long seed) {
    return FluxOps.unfold(new Random(seed), rng ->
      Optional.of(Tuples.of(rng.nextInt(), rng))
    );
  }

}
