package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorrepos;

    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private SectionRepository sectionrepos;

    @Override
    public List<Author> findAll(Pageable pageable)
    {
        ArrayList<Author> tempAuthorList = new ArrayList<>();
        authorrepos.findAll(pageable).iterator().forEachRemaining(tempAuthorList::add);
        return tempAuthorList;
    }

    @Override
    public Author save(Author authorData)
    {
        Author newAuthor = new Author();
        newAuthor.setFirstname(authorData.getFirstname());
        newAuthor.setLastname(authorData.getLastname());

        if (authorData.getBooks() != null)
        {
            for (Book b : authorData.getBooks())
            {
                newAuthor.getBooks().add(new Book(b.getBooktitle(), b.getIsbn(), b.getCopy(), b.getAuthors(), b.getSection()));
            }
        }
        return authorrepos.save(newAuthor);
    }
}
