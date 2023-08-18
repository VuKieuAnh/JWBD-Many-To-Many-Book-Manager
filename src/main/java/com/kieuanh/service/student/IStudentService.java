package com.kieuanh.service.student;

import com.kieuanh.model.Student;
import com.kieuanh.service.IService;

public interface IStudentService extends IService<Student> {
    Student findByName1(String name, String address);
}
