package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // PUT updates a books info (Title, Copyright, ISBN) but does NOT have to assign authors to the books
    // http://localhost:2019/data/books/{bookid}

    @PutMapping(value = "/books/{bookid}")
    public ResponseEntity<?> updateBookByBookId(
            @RequestBody Book updateBook, @PathVariable long bookid)
    {
        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST assigns a book already in the system (bookid) to an author already in the system (authorid) (see how roles are handled for users)
    // http://localhost:2019/data/books/{bookid}/authors/{authorid}

    @PostMapping(value = "/books/{bookid}/authors/{authorid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> assignSingleAuthorToBook(@PathVariable long bookid, @PathVariable long authorid)
    {
        bookService.addAuthorToBook(bookid, authorid);
//        authorService.addBookToAuthor(bookid, authorid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DEL deletes a book and the book author combinations - but does not delete the author records
    // http://localhost:2019/data/books/{bookid}
    @DeleteMapping(value = "/books/{bookid}")
    public ResponseEntity<?> deleteBookByBookId(@PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
