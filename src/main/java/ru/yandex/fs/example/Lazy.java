package ru.yandex.fs.example;

import ru.yandex.fs.weather.Weather;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

class Lazy {

  static Integer avg(List<Integer> xs) {
    return xs.isEmpty() ?
      0 :
      xs.stream().reduce(0, (acc, x) -> acc + x) / xs.size();
  }

  static Stream<Integer> nats = Stream.iterate(0, x -> x + 1);

  static Integer avgN(Stream<Integer> xs, Integer n) {
    return n == 0 ?
      0 :
      xs.limit(n).reduce(0, (acc, x) -> acc + x) / n;
  }

  static Stream<Integer> temp(Weather w, String city) {
    return Stream.generate(() -> w.temp(city));
  }

  static Stream<Boolean> coin() {
    var rnd = new Random();
    return Stream.generate(() -> rnd.nextBoolean());
  }

}
