# Дипломный проект по профессии «Тестировщик ПО»

## Описание приложения
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

Обычная оплата по дебетовой карте.
Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

сервису платежей, далее Payment Gate;
кредитному сервису, далее Credit Gate.
Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## ПО для работы с проектом:
 - Intellij IDEA;
 - Docker Desktop;
 - Chrome Google Браузер.

 ## Установка и запуск.

 1. Склонировать репозиторий на локальный ПК: 

     git clone git@github.com:Helena01101986/QA_diploma.git

2. Открыть проект в Intellij IDEA.
3. Запустить контейнеры MySQL, PostgreSQL, Node.js:

    docker-compose up

4. Запустить приложение (jar-file) для БД MySQL:

    java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./aqa-shop.jar

5. Запустить приложение (jar-file) для БД PostgreSQL:

    java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./aqa-shop.jar

6. Запустить тесты для MySQL:

    ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"

7. Запусстить тесты для PostgreSQL:

    ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

8. Для генерации и автоматического отчета Allure в браузере:

    ./gradlew allureServe
