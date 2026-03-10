<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Free Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        body { background-color: #f4f6f8; }
        .register-box {
            max-width: 700px;
            margin: 60px auto;
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .title-bar {
            background-color: #2e8cb8;
            color: white;
            padding: 12px;
            text-align: center;
            font-weight: 600;
            border-radius: 4px;
            margin-bottom: 25px;
        }
        .input-with-icon {
            position: relative;

        }

        .input-with-icon input {
            padding-right: 40px;

        }

        .feedback-icon {
            position: absolute;
            margin-top:14px;
            right: 12px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 18px;
            color: #28a745;
            pointer-events: none;
        }
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

<!-- Register Box -->
<div class="container">
    <div class="register-box">
        <div class="title-bar">Create Your Free Account</div>

        <form action="registerData" method="post" novalidate>

            <!-- First & Last Name -->
            <div class="row mb-3">
                <div class="col position-relative">
                    <label class="form-label">First Name</label>
                    <input type="text" id="firstName" name="firstName" class="form-control" required>
                    <div id="firstNameError" class="text-danger small mt-1"></div>
                </div>
                <div class="col position-relative">
                    <label class="form-label">Last Name</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" required>
                    <div id="lastNameError" class="text-danger small mt-1"></div>
                </div>
            </div>

            <!-- Email -->
            <div class="mb-3 position-relative input-with-icon">
                <label class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
                <span id="emailIcon" class="feedback-icon"></span>
                <div id="emailError" class="text-danger small mt-1"></div>
            </div>

            <!-- Password -->
            <div class="mb-3 position-relative">
                <label class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
                <div id="passwordError" class="text-danger small mt-1"></div>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-success">Register</button>
            </div>

        </form>
    </div>
</div>

<script>
// Field validation functions
function showError(input, message) {
    input.classList.add("is-invalid");
    input.classList.remove("is-valid");
    document.getElementById(input.id + "Error").innerText = message;
    const icon = document.getElementById(input.id + "Icon");
    if(icon) icon.innerText = "";
}

function showSuccess(input, showTick) {
    input.classList.remove("is-invalid");
    document.getElementById(input.id + "Error").innerText = "";
    const icon = document.getElementById(input.id + "Icon");

    if (input.id === "email" && showTick && icon) {
        icon.innerHTML = '<i class="bi bi-check-circle-fill text-success"></i>';
    }
}

// Real-time email check with AJAX
document.getElementById("email").addEventListener("input", function() {
    let emailInput = this;
    let value = emailInput.value.trim();
    const emailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;

    if(!emailRegex.test(value)) {
        showError(emailInput, "Email must end with @gmail.com");
        return;
    }

    fetch('checkEmail', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `email=${encodeURIComponent(value)}`
    })
    .then(resp => resp.text())
    .then(data => {
        if(data === "exists") {
            showError(emailInput, "Email already exists!!!");
        } else {
            showSuccess(emailInput, true);
        }
    })
    .catch(err => {
        console.error(err);
        showError(emailInput, "Error checking email");
    });
});

// Simple validation for other fields (optional)
document.getElementById("firstName").addEventListener("input", function() {
    if(!/^[A-Za-z]{2,}$/.test(this.value.trim()))
        showError(this, "Only alphabets allowed");
    else showSuccess(this, false);
});

document.getElementById("lastName").addEventListener("input", function() {
    if(!/^[A-Za-z]+( [A-Za-z]+)*$/.test(this.value.trim()))
        showError(this, "Only letters and spaces allowed");
    else showSuccess(this, false);
});

document.getElementById("password").addEventListener("input", function() {
    if(!/^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{6,}$/.test(this.value))
        showError(this, "Min 6 chars, 1 uppercase, 1 number, 1 symbol");
    else showSuccess(this, false);
});

</script>
</body>
</html>