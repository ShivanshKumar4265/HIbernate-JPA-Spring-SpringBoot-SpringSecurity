<%@page import="backend.modals.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="backend.DAO.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>


<style>
    body{
        margin:0;
        padding:0;
        font-family: Arial, sans-serif;
        background:#f0f2f5;
        display:flex;
        justify-content:center;
        align-items:center;
        height:100vh;
    }
    
    


    .login-box{
        background:white;
        padding:30px;
        width:300px;
        border-radius:10px;
        box-shadow:0px 0px 10px rgba(0,0,0,0.2);
    }

    .login-box h2{
        text-align:center;
        margin-bottom:20px;
    }

    .input-box{
        margin-bottom:15px;
    }
    .input-box label{
        display:block;
        margin-bottom:5px;
        font-weight:bold;
    }

    .input-box input{
        width:100%;
        padding:10px;
        border:1px solid #ccc;
        border-radius:5px;
    }

    .btn{
        width:100%;
        padding:10px;
        background:#007bff;
        color:white;
        border:none;
        border-radius:5px;
        font-size:16px;
        cursor:pointer;
    }

    .btn:hover{
        background:#0056b3;
    }
</style>

</head>


<body>

<! =i is th !>
	<%
		StudentDAO studentDAO = new StudentDAO();
	    ArrayList<Student> students = studentDAO.getAllStudents();
	%>
	
	<table border="1" width="100%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        
        <%for(Student student : students){%>
            <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getEmail()%></td>
                <td>Email</td>
            </tr>
        <%}%>
</body>
</html>