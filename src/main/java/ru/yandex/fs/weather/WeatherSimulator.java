package ru.yandex.fs.weather;

import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Function;

public class WeatherSimulator {

  public static Weather make() {
    var rnd = new Random();
    var l = 9;
    var r = 17;
    return new Weather() {
      public Integer temp(String city) {
        var t = rnd.nextInt(r - l) + l;
        System.out.println("    temp " + t);
        return t;
      }
    };
  }

  static <T> Flux<T> use(Integer token, Function<Weather, Flux<T>> f) {
    return Flux.using(
      () -> {
        System.out.println("  Entering the Weather [" + token + "] scope");
        return make();
      },
      f,
      w -> System.out.println("  Leaving the Weather [" + token + "] scope")
    );
  }

}
