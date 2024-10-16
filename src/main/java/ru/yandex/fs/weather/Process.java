package ru.yandex.fs.weather;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;
import ru.yandex.fs.flux.FluxOps;

import java.time.Instant;
import java.util.Optional;

public class Process {

  static Flux<Integer> tempN(Weather w, Conf c) {
    return FluxOps
      .unfold("Saint Petersburg", city ->
        Optional.of(Tuples.of(w.temp(city), city))
      )
      .delayElements(c.interval())
      .take(c.limit());
  }

  static Flux<Integer> temp(Conf c) {
    return Flux
      .fromIterable(c.tokens())
      .concatMap(t ->
        WeatherSimulator.use(t, w -> tempN(w, c))
      )
      .repeat();
  }

  static Flux<Integer> avg(Flux<Integer> xs) {
    return xs
      .reduce(Tuples.of(0, 0), (acc, x) ->
        Tuples.of(acc.getT1() + x, acc.getT2() + 1)
      )
      .map(t -> t.getT2() == 0 ? 0 : t.getT1() / t.getT2())
      .flux();
  }

  static Flux<Integer> avgs(Conf c) {
    return temp(c).window(c.window()).concatMap(xs -> avg(xs));
  }

  static Flux<Boolean> produce(Log<Tuple2<Integer, Instant>> log, Conf c) {
    return avgs(c).map(avg ->
      log.produce(Tuples.of(avg, Instant.now()))
    );
  }

  static Flux<Boolean> program(Conf c) {
    return LogSimulator.use(log -> produce(log, c));
  }

}
