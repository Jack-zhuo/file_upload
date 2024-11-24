<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: zy
  Date: 22/10/2024
  Time: 12:23 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传 & 文件下载</title>
</head>
<body>
<%-- entype -> encoding type encode --%>
 <form action="upload" method="post" enctype="multipart/form-data">
     用户名：<input type="text" name="username" required><br>
     选择文件：<input type="file" name="filename" required><br>
     <input type="submit" value="提交">
 </form>

<h1>文件列表</h1>
<ul>
    <%
        File folder = new File("E:\\zhuoCode\\javaCode\\2024\\10\\file_upload\\uploads");
        File[] files = folder.listFiles();
        if (files != null){
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();
    %>
    <li><a href="download?filename=<%= name %>"><%= name %></a></li>
    <%

            }
        }
    %>

</ul>
</body>
</html>
