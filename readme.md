# API Сервиса перевода денег с карты на карту

Данный проект представляет собой серверное приложение на Java, разработанное в рамках курсовой работы "Сетевой чат" на платформе Нетология.

## Описание

Веб-сервер, разработанный на базе SpringBoot v3. Представляет собой API для перевода денег с одной карты на другую.

Сервер по умолчанию запускается на порту 5500. Техническая документация для интеграции с API FRONT находится по адресу:

[`http://localhost:5500/swagger-ui/index.html`](http://localhost:5500/swagger-ui/index.html)

Для успешного перевода необходимо учитывать <b>особенности работы</b>

## Особенности работы

1. CVV код должен быть равен `111`
2. Номера карт отправителя и получателя не должны совпадать
3. Для подтверждения операции необходимо отправить код `0000`

## Логирование

Все логи записываются в файл `file.log`.

Формат логгирования:

```
[Wed May 01 14:57:10 MSK 2024] Operation ID: 6757 Result: CONFIRM_PENDING   // Дата, id и результат операции
From card: 1111222233334444                                                 // Карта отправителя
To card: 4444333322221111                                                   // Карта получателя
Value: 10.0                                                                 // Сумма перевода
Commission: 0.1                                                             // Комиссия сервиса
Currency: RUB                                                               // Валюта
```


## Требования

- Java 8 или выше
- Установленный Docker

## Запуск сервера

1. Склонируйте репозиторий в подготовленную для проекта папку `git clone https://github.com/PavelMart/money-transfer-service.git <PROJECT_FOLDER>`
2. Откройте проект в IDE
3. Запустите сервер.