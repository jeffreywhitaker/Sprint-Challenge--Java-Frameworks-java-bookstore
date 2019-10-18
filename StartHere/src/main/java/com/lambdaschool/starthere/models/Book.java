package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Book", description = "The Book Entity")
@Entity
@Table(name = "book")
public class Book extends Auditable
{
    // fields
    @ApiModelProperty(name = "bookid", value = "primary key for Book", required = true, example = "3")
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @ApiModelProperty(name = "booktitle", value = "name of Book", example = "Jeff's Story")
    @Column(nullable = false, unique = true)
    private String booktitle;

    @ApiModelProperty(name = "isbn", value = "ISBN of book", example = "ISBN HERE")
    @Column(nullable = false, unique = true)
    private String isbn;

    private int copy;

    @ManyToMany
    @JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "bookid")},
               inverseJoinColumns = {@JoinColumn(name = "authorid")})
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties({"books", "hibernateLazyInitializer"})
    private Section section;

    // constructors
    public Book()
    {
    }

    public Book(String booktitle, String isbn, int copy, List<Author> authors, Section section)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
        this.authors = authors;
        this.section = section;
    }

    // getters and setters
    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }

    public Section getSection()
    {
        return section;
    }

    public void setSection(Section section)
    {
        this.section = section;
    }

    // methods
}