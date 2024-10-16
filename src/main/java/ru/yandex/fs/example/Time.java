package ru.yandex.fs.example;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;
import ru.yandex.fs.flux.FluxOps;
import ru.yandex.fs.weather.Weather;

import java.time.Duration;
import java.util.Optional;

class Time {

  static Flux<Long> clock = Flux.interval(Duration.ofSeconds(1));

  static Flux<Integer> temp(Weather w) {
    return FluxOps.unfold("Saint Petersburg", city ->
      Optional.of(Tuples.of(w.temp(city), city))
    );
  }

  static Flux<Integer> tempSpaced(Weather w, Duration d) {
    return FluxOps
      .unfold("Saint Petersburg", city ->
        Optional.of(Tuples.of(w.temp(city), city))
      )
      .delayElements(d);
  }

}
