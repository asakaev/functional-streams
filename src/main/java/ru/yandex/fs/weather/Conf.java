package ru.yandex.fs.weather;

import java.time.Duration;
import java.util.List;

record Conf(List<Integer> tokens, Integer limit, Duration interval, Integer window) {}
