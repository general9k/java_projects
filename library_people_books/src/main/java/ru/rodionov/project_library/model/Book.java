package ru.rodionov.project_library.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Книга должна иметь название")
    @Size(min = 2, max = 100, message = "Название книги должно быть в пределах от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Книга должна иметь автора")
    @Size(min = 2, max = 100, message = "ФИО автора должно быть в пределах от 2 до 100 символов")
    private String author;
    @Min(value = 1800, message = "Год книги должен начинаться от 1800")
    private int year;

    public Book() {

    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
