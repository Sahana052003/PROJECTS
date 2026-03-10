<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Registration</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
/* Navbar Container */
.navbar-container{
    height:100px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    width:100%;
    padding-left:0;
    padding-right:0;
}

/* Navbar Brand */
.navbar-brand{
    margin-left:25px;
    font-weight:bold;
    font-size:2.5rem; /* Bigger letters */
    letter-spacing: 2px; /* Space between letters */
    text-transform: uppercase; /* Optional: makes letters all caps */
    text-shadow: 1px 1px 3px rgba(0,0,0,0.3); /* subtle shadow for pop */
}

/* Right Side Buttons */
.nav-right{
    display:flex;
    margin-right:25px;
    gap:20px;
}

/* Gradient Navbar: Light Purple to Dark Purple */
.bg-gradient-purple{
    background: linear-gradient(90deg, #9370DB, #4B0082); /* Light purple to dark purple */
}

/* Gradient Buttons with Shadow and Larger Size */
.btn-gradient-purple{
    background: linear-gradient(45deg, #B19CD9, #7A1FC0); /* Lighter purple to medium dark purple */
    color: white;
    border: none;
    font-weight: bold;
    font-size: 1.3rem; /* Bigger text */
    padding: 12px 28px; /* Bigger button size */
    border-radius: 8px;
    box-shadow: 0 6px 12px rgba(0,0,0,0.25); /* subtle shadow */
    transition: all 0.3s ease;
}

.btn-gradient-purple:hover{
    background: linear-gradient(45deg, #A083D1, #5D0E99); /* Slightly darker on hover */
    color: white;
    transform: scale(1.08); /* slightly bigger on hover */
    box-shadow: 0 8px 16px rgba(0,0,0,0.35); /* stronger shadow on hover */
}
</style>
</head>

<body class="bg-light">
<nav class="navbar bg-gradient-purple">
  <div class="container-fluid navbar-container">

    <!-- Left Side -->
    <a class="navbar-brand text-white" href="#">X-Workz</a>

    <!-- Right Side -->
    <div class="nav-right">
      <a class="btn btn-gradient-purple" href="login">Login</a>
      <a class="btn btn-gradient-purple" href="signup">SignUp</a>
    </div>

  </div>
</nav>
<!-- Navbar End -->
</body>
</html>