<%--
  Created by IntelliJ IDEA.
  User: yeemi
  Date: 2017/6/5 0005
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
</head>
<body>
hello<br>
<form action="/yeemin/addUser" method="post">
    <input name="username" type="text" />
    <input name="password" type="password" />
    <input type="submit" value="submit">
</form>
<br>
<c:forEach items="${user}" var="user" >
    ${user.id} &nbsp; ${user.username} &nbsp; ${user.password} <br>
</c:forEach>
${currentUserId}
</body>
<script type="text/javascript">
$().ready(function() {
    $.post('/yeemin/model', {}, function (data) {
        console.log(data);
        var obj = eval('('+data+')');
        console.log(obj);
    });
})
</script>
</html>
