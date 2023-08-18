package com.kieuanh.service.student;

import com.kieuanh.config.ConnectionJDBC;
import com.kieuanh.model.Student;

import java.sql.*;
import java.util.List;

public class StudentService implements IStudentService {
    Connection connection = ConnectionJDBC.getConnect();
    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public void save(Student p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Student o) {

    }

    @Override
    public Student findByName1(String name, String address) {
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where name=? and address=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name1 = resultSet.getString("name");
                String address1 = resultSet.getString("address");
                int id = resultSet.getInt("id");
                student = new Student(id, name1, address1);
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
