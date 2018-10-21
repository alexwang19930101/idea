<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<form action="hello/testFileUpload" method="post" enctype="multipart/form-data">
    desc:<input type="text" name="desc">
    <br>
    file:<input type="file" name="file">
    <br>
    <input type="submit" value="submit">
</form>
<br>
<br>
<form action="hello/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1">
    username:<input type="text" name="username">
    <br>
    email:<input type="text" name="email">
    <br>
    age:<input type="text" name="age">
    <br>
    <input type="submit" value="submit">
</form>

<<a href="hello/testMap">testMap</a>
<br><br>
<a href="hello/testModelAndView">testModelAndView</a>
<br><br>
<form action="hello/testpojo">
    username:<input type="text" name="username">
    <br>
    password:<input type="password" name="password">
    <br>
    email:<input type="text" name="email">
    <br>
    age:<input type="text" name="age">
    <br>
    province:<input type="text" name="address.province">
    <br>
    city:<input type="text" name="address.city">
    <br>
    <input type="submit" value="submit">
</form>

<a href="hello/testCookieValue">testCookieValue</a>
<br>
<a href="hello/testRequestParam?username=aaa&password=123">testRequestParam</a>

<h2><a href="hello/helloworld">Hello World!</a></h2>
<h2><a href="hello/testMethod">testMethod</a></h2>
<form action="hello/testMethod" method="post">
    <input type="submit">
</form>
<br>

<h2><a href="hello/testPathVariable/1">testPathVariable</a></h2>
<br>

<%--test REST--%>
<form action="hello/testRest" method="post">
    <input type="submit" value="test REST POST">
</form>
<br>

<form action="hello/testRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="test REST PUT">
</form>
<br>

<form action="hello/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="test REST DELETE">
</form>
<br>

<a href="hello/testRest/1">test REST GET</a>
<br><br>

<fmt:message key="i18n.user"></fmt:message>
<fmt:message key="i18n.password"></fmt:message>
</body>
</html>
