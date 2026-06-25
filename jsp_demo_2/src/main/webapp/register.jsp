<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
<style>
body { font-family: Arial; background: #f2f2f2; }
.container { width: 400px; margin: 50px auto; background: white; padding: 30px; border-radius: 8px; }
input, textarea { widt h: 100%; padding: 8px; margin: 10px 0; box-sizing: border-box; }
button { width: 100%; padding: 10px; margin-top: 15px; background: #007bff; color: white; border: none; cursor: pointer; border-radius: 4px; }
</style>
</head>
<body>
<div class="container">
<h2>Student Registration</h2>
<form action="register" method="get">
    <input type="text" name="name" placeholder="Name" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="text" name="phone" placeholder="Phone" required>
    <input type="date" name="dob" required>
    <textarea name="address" placeholder="Address"></textarea>
    <input type="text" name="college" placeholder="College" required>
    <input type="text" name="batch" placeholder="Batch">
    <input type="text" name="passoutYear" placeholder="Passout Year">
    <input type="text" name="parentPhone" placeholder="Parent Phone">
    <button type="submit">Register</button>
</form>
</div>
</body>
</html>