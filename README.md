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

### Требования:
- JDK 25+
- Maven
- PostgreSQL

### Postman
Коллекция запросов лежит в репозитории:
- `my-project\postman`

Импорт:
- Postman → Import → File → выбрать JSON → Import

### Эндпоинты (кратко)
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
