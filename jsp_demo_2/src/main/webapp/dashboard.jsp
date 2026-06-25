<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<style>

    *{
        margin:0;
        padding:0;
        box-sizing:border-box;
        font-family:Arial, sans-serif;
    }

    body{
        background:#f4f4f4;
    }

    .navbar{
        width:100%;
        background:#007bff;
        color:white;
        padding:15px 30px;
        display:flex;
        justify-content:space-between;
        align-items:center;
    }

    .navbar h2{
        font-size:24px;
    }

    .navbar a{
        color:white;
        text-decoration:none;
        background:red;
        padding:8px 15px;
        border-radius:5px;
    }

    .container{
        display:flex;
    }

    .sidebar{
        width:220px;
        height:100vh;
        background:#222;
        padding-top:20px;
    }

    .sidebar a{
        display:block;
        color:white;
        text-decoration:none;
        padding:15px 20px;
    }

    .sidebar a:hover{
        background:#444;
    }

    .content{
        flex:1;
        padding:30px;
    }

    .cards{
        display:flex;
        gap:20px;
        margin-top:20px;
    }

    .card{
        background:white;
        padding:20px;
        width:200px;
        border-radius:10px;
        box-shadow:0px 0px 10px rgba(0,0,0,0.1);
        text-align:center;
    }

    .card h3{
        margin-bottom:10px;
        color:#333;
    }

    .card p{
        font-size:22px;
        color:#007bff;
        font-weight:bold;
    }

</style>

</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <h2>My Dashboard</h2>
        <a href="#">Logout</a>
    </div>

    <div class="container">

        <!-- Sidebar -->
        <div class="sidebar">
            <a href="#">Home</a>
            <a href="#">Profile</a>
            <a href="#">Students</a>
            <a href="#">Settings</a>
        </div>

        <!-- Main Content -->
        <div class="content">

            <h1>Welcome User</h1>
            <p>This is a simple static dashboard UI.</p>

            <div class="cards">

                <div class="card">
                    <h3>Total Students</h3>
                    <p>120</p>
                </div>

                <div class="card">
                    <h3>Total Courses</h3>
                    <p>15</p>
                </div>

                <div class="card">
                    <h3>Total Faculty</h3>
                    <p>25</p>
                </div>

            </div>

        </div>

    </div>

</body>
</html>