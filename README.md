# Учебные проекты

## Library1_JDBC - учебный проект, написанный на Spring MVC через JDBC Template. Без Hibernate. 

### Задача: реализовать связь между сущностью человек и книга, установить связь один-ко-многим. Человек может принимать в свою власть всего 1 книгу. Эта книга не может быть у нескольких человек. Отображение владения книгой должно отображаться на странице у человека и у книги.

- Страница зарегистрированных пользователей по GET запросу /people. 

 <img src="img/library_jdbc1.png" alt="library_jdbc1" width="45%" height="45%" />
 
- Страница существующих книг в библиотеке по GET запросу /books.

 <img src="img/library_jdbc2.png" alt="library_jdbc2" width="45%" height="45%" />
 
- Страница регистрации пользователя. 

 <img src="img/library_jdbc3.png" alt="library_jdbc3" width="45%" height="45%" />
 
- Страница самого пользователя: имя, год рождения, какие книги имеются у пользователя.

 <img src="img/library_jdbc4.png" alt="library_jdbc4" width="45%" height="45%" />
 
- Страница самой книги: название, автор книги, год создания, кому можно назначить книгу.

 <img src="img/library_jdbc5.png" alt="library_jdbc5" width="45%" height="45%" />
 
- Назначили книгу одному пользователю. Теперь данная книга находится во власти пользователя.

 <img src="img/library_jdbc6.png" alt="library_jdbc6" width="45%" height="45%" />
 
- Теперь на странице у пользователя также отображается данная книга во владении.

<img src="img/library_jdbc7.png" alt="library_jdbc7" width="45%" height="45%" />
 
- Удалили книгу из библиотеки - удалилась также данная книга у пользователя.

 <img src="img/library_jdbc8.png" alt="library_jdbc8" width="45%" height="45%" />

## Library2_JPA - учебный проект, написанный на Spring MVC с использованием Spring JPA, где под капотом Hibernate.

- <img src="img/MVC1.png" alt="MVC1" width="65%" height="65%" />

## project3_REST - учебный проект, написанный на Spring Boot, Spring Data JPA, Spring Validator, Spring WEB с использованием БД PostgreSQL.
