package com.kieuanh.service.category;

import com.kieuanh.model.Category;
import com.kieuanh.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    List<Category> findAllByBookId(int book_id);
}
