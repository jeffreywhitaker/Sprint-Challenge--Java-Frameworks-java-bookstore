package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

    Book save(Book book);

    void delete(long id);

    Book update(Book book, long id);

    Book addAuthorToBook(long bookid, long authorid);
}
