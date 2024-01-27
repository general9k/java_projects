package ru.rodionov.project_library.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rodionov.project_library.model.Book;
import ru.rodionov.project_library.model.Person;

import java.util.List;
import java.util.Optional;


@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Book> show(int id, String title, String author, int year) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id <> ? and title=? and author=? and year=?",
                        new Object[]{id, title, author, year},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    // Person join Book по ключу person_id, чтобы получить человека у которого эта книга
    // findAny() - возвращает хотя бы какой-то
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                        "WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    // Запрос для освобождения книги от пользователя
    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
