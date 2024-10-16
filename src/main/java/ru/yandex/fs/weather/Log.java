package ru.yandex.fs.weather;

interface Log<T> {
  Boolean produce(T value);
}
