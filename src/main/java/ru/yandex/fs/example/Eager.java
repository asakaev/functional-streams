package ru.yandex.fs.example;

import java.util.List;

class Eager {

  static Integer avg(List<Integer> xs) {
    if (xs.isEmpty()) return 0;
    else {
      var acc = 0;
      for (Integer x : xs) {
        acc = acc + x;
      }
      return acc / xs.size();
    }
  }

}
