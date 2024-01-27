package ru.rodionov.project_library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rodionov.project_library.dao.BookDAO;
import ru.rodionov.project_library.model.Book;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (bookDAO.show(book.getId(), book.getTitle(), book.getAuthor(), book.getYear()).isPresent()) {
            errors.rejectValue("year", "", "Данная книга уже имеется в каталоге");
        }
    }
}
