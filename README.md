## REST API для управления проектами и задачами

### Стек
- Java: 25
- Spring Boot: 3.5.6
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- Bean Validation
- PostgreSQL
- Maven
- Lombok

### Возможности
- Projects:
  - CRUD: создание, получение всех, получение по id, частичное обновление, удаление
  - Пагинация
  - Поиск/фильтры:
    - по статусу выполнения
    - по ключевому слову (название/описание)
    - по дате создания
    - по дате обновления
  
- Tasks:
  - CRUD: создание в проекте, получение всех, получение по id, частичное обновление, удаление
  - Пагинация
  - Поиск/фильтры:
    - по проекту
    - по статусу
    - по ключевому слову (название/описание)
    - по дате создания
    - по дате обновления
  
- Глобальная обработка ошибок
- DTO + мапперы
- Транзакционность сервисов

### Запуск
#### Через Docker (рекомендуется)

**Требования:**
- Docker Desktop установлен и запущен

**Переменные окружения:**
Перед запуском нужно задать переменные окружения:

**CMD:**
- `set DB_URL=jdbc:postgresql://localhost:5432/ProjectsAndTasks`
- `set DB_URL_DOCKER=jdbc:postgresql://db:5432/ProjectsAndTasks`
- `set DB_USERNAME=postgres`
- `set DB_PASSWORD=postgres`
- `docker compose up --build`

**`DB_URL`** — JDBC URL для подключения к PostgreSQL
**`DB_URL_DOCKER`** — JDBC URL для подключения к PostgreSQL в Docker (localhost -> db)
**`DB_USERNAME`** — имя пользователя PostgreSQL
**`DB_PASSWORD`** — пароль пользователя PostgreSQL

**PowerShell:**
- `$env:DB_URL="jdbc:postgresql://db:5432/ProjectsAndTasks"`
- `$env:DB_USERNAME="postgres"`
- `$env:DB_PASSWORD="postgres"`
- `docker compose up --build`

**После запуска:**
- API доступно на `http://localhost:8080`
- PostgreSQL доступен на `localhost:5432`

**Остановка:**
CMD/PowerShell:
`Ctrl + C`

#### Через IntelliJ IDEA

**Требования:**
- IntelliJ IDEA установлен
- JDK 25+ установлен
- PostgreSQL запущен локально
- База данных `ProjectsAndTasks` создана

**Переменные окружения:**
1. **Run → Edit Configurations...**
2. Выбери конфигурацию Spring Boot (или создай новую)
3. В разделе **Environment variables** добавь переменные

### Postman
Коллекция запросов лежит в репозитории:
- `my-project\postman`

Импорт:
- Postman → Import → File → выбрать JSON → Import

### Эндпоинты
Projects:
- `POST /projects`
- `GET /projects`
- `GET /projects/page?page=0&size=5`
- `GET /projects/{id}`
- `GET /projects/search?keyword=...`
- `GET /projects/created?createdAt=YYYY-MM-DD`
- `GET /projects/updated?updatedAt=YYYY-MM-DD`
- `GET /projects/completed?completed=false`
- `PATCH /projects/{id}`
- `DELETE /projects/{id}`

Tasks:
- `POST /tasks?projectId=...`
- `GET /tasks`
- `GET /tasks/{id}`
- `GET /tasks/project?projectId=...`
- `GET /tasks/status?status=TODO|IN_PROGRESS|DONE|BLOCKED`
- `GET /tasks/keyword?keyword=...`
- `GET /tasks/created?createdAt=YYYY-MM-DD`
- `GET /tasks/updated?updatedAt=YYYY-MM-DD`
- `GET /tasks/page?page=0&size=5`
- `PATCH /tasks/{id}`
- `DELETE /tasks/{id}`

### Формат ошибок
Ошибки возвращаются в JSON и содержат поля:
- `timestamp`
- `status`
- `error`
- `message`
