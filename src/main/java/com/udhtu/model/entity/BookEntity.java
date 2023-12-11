package com.udhtu.model.entity;

import com.udhtu.model.dto.AuthorDTO;
import com.udhtu.model.enumerated.EBookType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class BookEntity extends BasedEntity<Long> {


    @Column(name = "date_of_issue", length = 50)
    private String date;
    @Column(name = "edition", length = 50)
    private String edition;
    @OneToOne(targetEntity = AuthorEntity.class)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
    @Column(name = "name", length = 50)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "book_type", length = 50)
    private EBookType bookType;
    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "count_of_pages", length = 50)
    private int countOfPages;
    @Column(name = "rating", length = 50)
    private float rating;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EBookType getBookType() {
        return bookType;
    }

    public void setBookType(EBookType bookType) {
        this.bookType = bookType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return countOfPages == that.countOfPages && Float.compare(rating, that.rating) == 0 && Objects.equals(date, that.date) && Objects.equals(edition, that.edition) && Objects.equals(author, that.author) && Objects.equals(name, that.name) && bookType == that.bookType && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, edition, author, name, bookType, description, countOfPages, rating);
    }
}
