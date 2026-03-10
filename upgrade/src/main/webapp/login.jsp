<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Login</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f4f6f8;
        }

        .login-box {
            max-width: 420px;
            margin: 80px auto;
            background: white;
            padding: 35px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .login-title {
            font-weight: 600;
            text-align: center;
            margin-bottom: 25px;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold text-primary" href="/">SignUp</a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="#">What We Do</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Create a SignUp</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Find My SignUp</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Help</a></li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item me-3">
                    <form action="login" method="get">
                        <button type="submit" class="btn btn-link nav-link text-dark p-0">Log In</button>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="register" method="post">
                        <button type="submit" class="btn btn-link nav-link text-dark p-0">Register</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Login Box -->
<div class="container">
    <div class="login-box">

        <h4 class="login-title">Dashboard Login</h4>

        <!-- Error Message -->
        <c:if test="${not empty loginError}">
            <div id ="loginErrorBox" class="alert alert-danger text-center">
                ${loginError}
            </div>
        </c:if>

        <form action="loginData" method="post">

            <!-- Email -->
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="text"
                       name="email"

                       class="form-control ${not empty loginError ? 'is-invalid' : ''}"
                       placeholder="you@gmail.com">


            </div>

            <!-- Password -->
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password"
                       name="password"
                       class="form-control ${not empty loginError ? 'is-invalid' : ''}">
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-success">Log In</button>
            </div>

        </form>

    </div>
</div>

<script>
window.addEventListener("DOMContentLoaded", function () {

    const errorBox = document.getElementById("loginErrorBox");

    if (errorBox) {

        setTimeout(function () {

            errorBox.style.transition = "opacity 0.5s ease";
            errorBox.style.opacity = "0";

            document.querySelectorAll(".is-invalid").forEach(function(input){
                input.classList.remove("is-invalid");
            });

            setTimeout(function () {
                errorBox.remove();
            }, 1000);

        }, 1000);
    }

});
</script>
</body>
</html>