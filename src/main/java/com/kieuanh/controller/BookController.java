package com.kieuanh.controller;

import com.kieuanh.model.Book;
import com.kieuanh.service.book.BookService;
import com.kieuanh.service.book.IBookService;
import com.kieuanh.service.category.CategoryService;
import com.kieuanh.service.category.ICategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookController", value = "/books")
public class BookController extends HttpServlet {

    ICategoryService categoryService = new CategoryService();
    IBookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showFormCreate(req, resp);
                break;
            default:
                showAllBook(req, resp);
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/create.jsp");
        req.setAttribute("categories", categoryService.findAll());
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllBook(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/index.jsp");
        List<Book> books = bookService.findAll();
        req.setAttribute("books", books);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createNewBook(req, resp);
                break;
            default:
//                showAllBook(req, resp);
        }
    }

    private void createNewBook(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String description = req.getParameter("description");
        String[] categoriesStr = req.getParameterValues("categories");
        int[] categories = new int[categoriesStr.length];
        for (int i = 0; i < categoriesStr.length; i++) {
            categories[i] = Integer.parseInt(categoriesStr[i]);
        }

        Book book = new Book(name, author, description);
        bookService.save(book, categories);


    }
}
