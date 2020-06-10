<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chengqj
  Date: 2019/11/20
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" align="center" width="50%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <td>${user.userid}</td>
                <td>${user.userName}</td>
                <td>${user.userAge}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
