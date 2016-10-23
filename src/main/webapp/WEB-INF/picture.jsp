<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form  action="/secondhandbook/picture/uploadpicture" method="post" enctype="multipart/form-data">
      <table border="0">
        <tr>
          <td>用户名:</td>
          <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
          <td>密码:</td>
          <td><input type="password" name="password" id="password"></td>
        </tr>
         <tr>
          <td>相片:</td>
          <td><input type="file" name="pic" id="pic"></td>
        </tr>
        <tr>
          <td>相片:</td>
          <td><input type="file" name="pic2" id="pic2"></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="submit"></td>
        </tr>
      </table>
    </form>
</body>
</html>