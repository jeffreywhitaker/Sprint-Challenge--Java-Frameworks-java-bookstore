package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Section", description = "The Section Entity")
@Entity
@Table(name = "section")
public class Section extends Auditable
{
    // fields
    @ApiModelProperty(name = "sectionid", value = "primary key for Section", required = true, example = "3")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    @ApiModelProperty(name = "sectionname", value = "name of Section", example = "Mystery")
    @Column(nullable = false, unique = true)
    private String sectionname;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("books")
    private List<Book> books = new ArrayList<>();

    // constructors
    public Section()
    {
    }

    public Section(String sectionname, List<Book> books)
    {
        this.sectionname = sectionname;
        this.books = books;
    }

    // getters and setters
    public long getSectionid()
    {
        return sectionid;
    }

    public void setSectionid(long sectionid)
    {
        this.sectionid = sectionid;
    }

    public String getSectionname()
    {
        return sectionname;
    }

    public void setSectionname(String sectionname)
    {
        this.sectionname = sectionname;
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
