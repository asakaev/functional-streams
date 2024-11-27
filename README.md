## Функциональные стримы

[Смотреть доклад на Joker 2024](https://vkvideo.ru/video-796_456240559).

### Моделирование процессов на Java
Дополнение к докладу: примеры кода и материалы, показывающие, как использовать стримы на Java для моделирования процессов, управления временем и состоянием.

### Задача
* Хранить данные по средней температуре за каждый час (41 измерений в час)
* Ограничение на количество вызовов к сервису погоды — 1000 запросов в сутки (в среднем запрос в 87 секунды)

### Коротко о погоде
* Один Log на время жизни программы
* Два Weather, которые меняются каждые три вызова
* Публикация в Log средних по двум значениям температуры
* Остановка после четырёх публикаций

```
limit=3, window=2, interval=1s, take=4

Entering the Log scope
  Entering the Weather [1] scope
    temp 13
    temp 11
  produce [12,2024-10-14T13:21:11.644038Z]
    temp 9
  Leaving the Weather [1] scope
  Entering the Weather [2] scope
    temp 14
  produce [11,2024-10-14T13:21:13.664125Z]
    temp 14
    temp 10
  produce [12,2024-10-14T13:21:15.671659Z]
  Leaving the Weather [2] scope
  Entering the Weather [1] scope
    temp 10
    temp 12
  produce [11,2024-10-14T13:21:17.679763Z]
  Leaving the Weather [1] scope
Leaving the Log scope
```

### Почитать
* [Machines](https://disk.yandex.ru/i/xEKEJZ5xJAsx2w), Rúnar Óli Bjarnason
* [Folds and unfolds all around us](http://conal.net/talks/folds-and-unfolds.pdf), Conal Elliott

### Посмотреть
[DDDamn good! на Joker 2021](https://vkvideo.ru/video-796_456240246)

### Благодарности
* [Александр Нозик](https://jokerconf.com/persons/c40622f2dc3a44169290f05771792410/)
* [Андрей Кулешов](https://jokerconf.com/persons/7516f53351294c259e57faf8a41d4c6a/)
* [Владимир Попов](https://jokerconf.com/archive/2021/persons/2krs7jv9njfoh4bf0lmi5p/)
