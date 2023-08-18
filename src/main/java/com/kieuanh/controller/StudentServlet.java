package com.kieuanh.controller;

import com.kieuanh.model.Student;
import com.kieuanh.service.student.IStudentService;
import com.kieuanh.service.student.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    IStudentService service = new StudentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("action");
        switch (act){
            case "create":
                showFormLogin(request, response);
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
//        String act1 = request.getParameter("name");
        System.out.println("do get student controller " + act );
    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("student/login.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("action");
        switch (act){
            case "create":
                login(request);
                break;
            default:
                System.out.println("do post student controller");
        }
    }

    private void login(HttpServletRequest request) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Student student1 = service.findByName1(name, address);
        if (student1 != null) {
            System.out.println("Dang nhap thanh cong");
        }
        else {
            System.out.println("Dang nhap sai");
        }
    }
}
