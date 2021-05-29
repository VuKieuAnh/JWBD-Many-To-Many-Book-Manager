package com.kieuanh.model;

import java.util.List;

public class Book {
    private int id;
    private String name;
    private String author;
    private String description;
    private List<Category> categories;

    public Book(String name, String author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Book() {
    }

    public Book(int id, String name, String author, String description, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.categories = categories;
    }

    public Book(int id, String name, String author, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
