package com.udhtu.model.dto;

import com.udhtu.model.enumerated.EBookType;

public class BookDTO
        extends BasedDTO<Long> {

    private String date;
    private String edition;
    private AuthorDTO author;
    private String name;
    private EBookType bookType;
    private String description;
    private int countOfPages;
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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
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

    public void getBook(int id) {
        System.out.println("Id: " + getId() + " Name: " + this.name + " Author: " + this.author + " DateOfIssue: " + this.date + " Rating: " + this.rating + " Type: " + this.bookType + " Pages: " + this.countOfPages + " Edition: " + this.edition + " Description: " + this.description);
    }

}
