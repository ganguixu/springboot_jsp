<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="/static/common/jquery-3.6.0.min.js"></script>
    <script src="/static/user/user.js"></script>
</head>
<body>
    <form action="login" method="post">
        用户名：<input type="text" name="name" /><br>
        密码：<input type="password" name="pass" /><br>
        <button type="submit">login</button>
    </form>
    <form action="/query" method="post">
        <button id="but">查看</button>
    </form>
</body>
</html>
