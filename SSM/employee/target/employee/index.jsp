<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<form action="/testFileUpload" method="post" enctype="multipart/form-data">
    file:<input name="upfile" type="file">
    desc:<input type="text" name="desc">
    <input type="submit" value="submit">
</form>
</body>
</html>
