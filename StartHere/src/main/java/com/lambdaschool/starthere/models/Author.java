package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Author", description = "The Author Entity")
@Entity
@Table(name = "author")
public class Author extends Auditable
{
    // fields
    @ApiModelProperty(name = "authorid",
                      value = "primary key for Author",
                      required = true,
                      example = "3")
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name = "lastname",
                      value = "author's last name",
                      example = "King")
    private String lastname;

    private String firstname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    // constructors
    public Author()
    {
    }

    public Author(String lastname, String firstname, List<Book> books)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.books = books;
    }

    // getters and setters
    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    // methods
}
