package ru.yandex.fs.weather;

import java.time.Duration;
import java.util.List;

public class Main {

  /**
   * new Conf(List.of(1, 2), 1000, Duration.ofSeconds(44), 81)
   */
  public static void main(String[] args) {
    var c = new Conf(List.of(1, 2), 3, Duration.ofSeconds(1), 2);
    Process.program(c).take(4).then().block();
  }

}
