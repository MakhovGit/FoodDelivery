# FoodDelivery
FoodDelivery - это приложение, которое имитирует интерфейс типичного приложения доставки пиццы. В качестве источника данных 
для главного экрана используется Skyeng Dictionary API.

Возможности:
- Навигация между тремя экранами (реализован только главный экран, вместо экранов профиля пользователя и корзины стоят заглушки)
- Выбор города пользователя
- Прокрутка списка баннеров
- Прокрутка списка кнопок меню
- Прокрутка списка продуктов (при этом список кнопок меню прилипает к верхнему бару)
- Показ сообщений об ошибке при невозможности загрузить данные по Skyeng Dictionary API

Используемые технологии:
- MVVM
- Coroutines
- Koin
- Android Jetpack Navigation
- Retrofit
- Coil
- Skyeng Dictionary API

Скриншоты:

<div align="center">
    <img src="/screenshots/Screenshot_01.png" width="400px"><p>Главный экран сразу после запуска приложения</p></img> 
    <br>
    <img src="/screenshots/Screenshot_02.png" width="400px"><p>Главный экран после прокрутки списка продуктов</p></img> 
    <br>
    <img src="/screenshots/Screenshot_03.png" width="400px"><p>Экран профиля пользователя</p></img> 
    <br>
    <img src="/screenshots/Screenshot_04.png" width="400px"><p>Экран корзины</p></img> 
    <br>
    <img src="/screenshots/Screenshot_05.png" width="400px"><p>Сообщение об ошибке загрузки данных</p></img> 
</div>
