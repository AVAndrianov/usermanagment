# User Management

## Описание проекта

Приложение для управления, подписками на сервисы. Приложение состоит из двух основных компонентов: бекенда на Java и
базы данных H2.

## Основные компоненты

- **Backend:** Java приложение для обработки запросов и взаимодействия с базой данных.
- **Database:** H2 для хранения данных о подписках и пользователях.

## Требования

- Docker
- Docker Compose

## Запуск приложения

### Локальный запуск

1. Клонируйте репозиторий:

    ```bash
    git clone https://github.com/AVAndrianov/usermanagment.git
    cd usermanagment
    ```

2. Перейдите в ветку мастер:

   ```bash
   git checkout master
   ``` 

3. Обновление переменной среды чтобы она указывала на Java 17:

   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home)
   ```  

4. Собрать JAR:

    ```bash
    mvn clean package
    ```

5. Запустите контейнеры:

    ```bash
    docker compose up
    ```

6. Откройте браузер и перейдите по адресу:

    - Backend: `http://localhost:8080`

## API эндпоинты

### Пользователи

- **POST /users** Создать пользователя.
- **GET /users/{id}** Получить информацию о пользователе.
- **PUT /users/{id}** Обновить пользователя.
- **DELETE /users/{id}** Удалить пользователя.

### Подписки

- **POST /users/{id}/subscriptions** Добавить подписку.
- **GET /users/{id}/subscriptions** Получить подписки пользователя.
- **DELETE /users/{id}/subscriptions/{sub_id}** Удалить подписку.
-
- **GET /subscriptions/top** Получить ТОП-3 популярных подписок.

PostmanCollection - в корне проекта
