<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 4/4/18
  Time: 5:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <g:each in="${userList}" var="user">
        ${user.toString()}
    </g:each>

</body>
</html>