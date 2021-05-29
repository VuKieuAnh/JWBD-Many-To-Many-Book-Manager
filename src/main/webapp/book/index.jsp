<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 29/05/2021
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sach sach</h1>
<table border="1px solid">
    <tr>
        <th>#</th>
        <th>ten</th>
        <th>tac gia</th>
        <th>Danh muc</th>
    </tr>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.name}</td>
            <td>${b.author}</td>
            <td>
                <c:forEach items="${b.categories}" var="c">
                    <span>${c.name}</span> &nbsp;
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
