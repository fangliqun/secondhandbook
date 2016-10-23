<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;utf-8">
<title>upload</title>
</head>
<body>  
        <h1>Please upload a file</h1>  
        <form method="post" action="/secondhandbook/upload/uploadFile" enctype="multipart/form-data">   
            <input type="file" name="files"/>  
            <input type="file" name="files"/>
            <input type="file" name="files"/>
            <input type="submit"/>  
        </form>  
    </body>  
</html>