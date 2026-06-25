<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>

<style>

body{
    font-family: Arial;
    background:#f2f2f2;
}

.container{
    width:500px;
    margin:20px auto;
    background:white;
    padding:20px;
    border-radius:10px;
}

input, textarea{
    width:100%;
    padding:10px;
    margin-top:10px;
}

button{
    width:100%;
    padding:10px;
    margin-top:15px;
    background:blue;
    color:white;
    border:none;
    cursor:pointer;
}

</style>

</head>
<body>

<div class="container">

<h2>Student Registration Form</h2>

<form action="register" method="get">

    Student Name
    <input type="text" name="name" required>

    Email
    <input type="email" name="email" required>

    Phone Number
    <input type="text" name="phone" required>

    Date of Birth
    <input type="date" name="dob" required>

    Address
    <textarea name="address"></textarea>

    College Name
    <input type="text" name="college" required>

    Batch
    <input type="text" name="batch" placeholder="2022-2026">

    Year of Passout
    <input type="text" name="passoutYear">

    Parent Phone Number
    <input type="text" name="parentPhone">

    <button type="submit">Register Student</button>

</form>

</div>

</body>
</html>