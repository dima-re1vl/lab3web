package com.udhtu.model.dto;

import java.util.List;

public class AuthorDTO extends BasedDTO<Long>{

    private String firstName;
    private String lastName;

    private String books;


    public String getAuthor() {
        return this.firstName + ' ' + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }
}
