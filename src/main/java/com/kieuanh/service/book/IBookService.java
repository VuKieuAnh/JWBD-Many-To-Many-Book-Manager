package com.kieuanh.service.book;

import com.kieuanh.model.Book;
import com.kieuanh.service.IService;


public interface IBookService extends IService<Book> {
    public void save(Book p, int[] categories);
}
