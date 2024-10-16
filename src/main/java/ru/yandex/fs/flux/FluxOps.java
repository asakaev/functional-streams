package ru.yandex.fs.flux;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Optional;
import java.util.function.Function;

public class FluxOps {

  /**
   * https://github.com/reactor/reactor-core/pull/3897
   */
  public static <T, S> Flux<T> unfold(S init, Function<S, Optional<Tuple2<T, S>>> f) {
    return Flux.generate(() -> init, (s, sink) -> {
      var res = f.apply(s);
      if (!res.isPresent()) {
        sink.complete();
        return s;
      } else {
        sink.next(res.get().getT1());
        return res.get().getT2();
      }
    });
  }

}
