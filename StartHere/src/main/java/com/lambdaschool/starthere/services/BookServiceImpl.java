package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        ArrayList<Book> tempBookList = new ArrayList<>();
        bookrepos.findAll(pageable).iterator().forEachRemaining(tempBookList::add);
        return tempBookList;
    }

    @Override
    public Book save(Book book)
    {
        /*Author newAuthor = new Author();
        newAuthor.setFirstname(authorData.getFirstname());
        newAuthor.setLastname(authorData.getLastname());

        if (authorData.getBooks() != null)
        {
            for (Book b : authorData.getBooks())
            {
                newAuthor.getBooks().add(new Book(b.getBooktitle(), b.getIsbn(), b.getCopy(), b.getAuthors(), b.getSection()));
            }
        }
        return authorrepos.save(newAuthor);*/
        return null;
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }

        currentBook.setCopy(book.getCopy());

        if (book.getIsbn() != null)
        {
            currentBook.setIsbn(book.getIsbn());
        }

        return bookrepos.save(currentBook);
    }

    @Override
    public Book addAuthorToBook(long bookid, long authorid)
    {
        Book updateBook = bookrepos.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(bookid)));

        Author updateAuthor = authorrepos.findById(authorid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(authorid)));

        List updateAuthorList = new ArrayList();
        updateAuthorList.add(updateAuthor);

        updateBook.setAuthors(updateAuthorList);

        return bookrepos.save(updateBook);
    }
}
