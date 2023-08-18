package com.kieuanh.service.book;

import com.kieuanh.config.ConnectionJDBC;
import com.kieuanh.model.Book;
import com.kieuanh.model.Category;
import com.kieuanh.service.category.CategoryService;
import com.kieuanh.service.category.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {
    public static final String SELECT_ALL_BOOK = "select * from book;";
    public static final String INSERT_NEW_BOOK = "insert into book (name, author, description) VALUE (?, ?, ?);";
    public static final String INSERT_NEW_BOOK_CATEGORY = "insert into book_category (category_id, book_id) VALUE (?, ?);";
    Connection connection = ConnectionJDBC.getConnect();
    ICategoryService categoryService = new CategoryService();

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOK);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //lay ra ten, tac gia, mo ta cua sach
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                // lay ra danh sach danh muc theo id cua sach
                List<Category> categories = categoryService.findAllByBookId(id);
                Book book = new Book(id, name, author, description, categories);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public void save(Book p) {

    }

    @Override
    public void save(Book p, int[] categories) {

        int book_id=0;

        //b1: ghi thong tin cua sach vao bang book
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(INSERT_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            statement1.setString(1, p.getName());
            statement1.setString(2, p.getDescription());
            statement1.setString(3, p.getAuthor());
//            CallableStatement statement1 =
//                    connection.prepareCall("{CALL createNewBook(?, ?, ?)}");
//            statement1.setString(1, p.getName());
//            statement1.setString(2, p.getDescription());
//            statement1.setString(3, p.getAuthor());

            int a1 = statement1.executeUpdate();
            //b2: lay id cua sach vua dc ghi ra
            ResultSet resultSet = statement1.getGeneratedKeys();
            while (resultSet.next()){
                System.out.println(resultSet);
                book_id= resultSet.getInt(1);
            }

            //b3: ghi them ban ghi vao bang trung gian book_category
            //1. book_id, category_id
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_BOOK_CATEGORY);
            for (int id_category: categories
                 ) {
                statement.setInt(1,id_category);
                statement.setInt(2,book_id);
                statement.executeUpdate();
            }
//            connection.commit();


        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }





    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Book book) {

    }
}
