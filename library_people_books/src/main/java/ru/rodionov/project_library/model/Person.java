package ru.rodionov.project_library.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 100, message = "ФИО человек должно быть в пределах от 2 до 100 символов")
    private String fullName;

    @Min(value = 0, message = "Возраст больше нуля")
    private int yearOfBirth;

    public Person() {

    }

    public Person(int id, String full_name, int yearOfBirth) {
        this.id = id;
        this.fullName = full_name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
