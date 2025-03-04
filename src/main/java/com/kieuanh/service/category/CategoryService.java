package com.kieuanh.service.category;

import com.kieuanh.config.ConnectionJDBC;
import com.kieuanh.model.Category;
import com.kieuanh.model.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    Connection connection = ConnectionJDBC.getConnect();
    public static final String SELECT_ALL_BOOK = "select * from category;";
    public static final String SELECT_CATEGORY_BY_B_ID = "select id, name, description from category join book_category bc on category.id = bc.category_id and bc.book_id=?";


    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOK);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Category category = new Category(id, name, description);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> findAllByBookId(int book_id) {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement1 = connection.prepareStatement(SELECT_CATEGORY_BY_B_ID);
            statement1.setInt(1, book_id);
            ResultSet resultSet = statement1.executeQuery();
//            ResultSet resultSet1 = statement1.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Category category = new Category(id, name, description);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Category category) {

    }

    private final String countCategory = "select category_id as id, name, count(bc.book_id) as count\n" +
            "from category join book_category bc\n" +
            "    on category.id = bc.category_id\n" +
            "group by category_id;";
    @Override
    public List<CategoryDTO> countBookCategory() {
        Connection connection = ConnectionJDBC.getConnect();
        List<CategoryDTO> categories = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(countCategory);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count");
                CategoryDTO categoryDTO = new CategoryDTO(id, name, count);
                categories.add(categoryDTO);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        List<CategoryDTO> categories = categoryService.countBookCategory();
        System.out.println(categories);

    }
}
