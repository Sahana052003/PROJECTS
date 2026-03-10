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
    font-size:2.5rem;
    letter-spacing: 2px;
    text-transform: uppercase;
    text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
}

/* Right Side Buttons */
.nav-right{
    display:flex;
    margin-right:25px;
    gap:20px;
}

/* Gradient Navbar */
.bg-gradient-purple{
    background: linear-gradient(90deg, #9370DB, #4B0082);
}

/* Gradient Buttons */
.btn-gradient-purple{
    background: linear-gradient(45deg, #B19CD9, #7A1FC0);
    color: white;
    border: none;
    font-weight: bold;
    font-size: 1.3rem;
    padding: 12px 28px;
    border-radius: 10px;
    box-shadow: 0 6px 12px rgba(0,0,0,0.25);
    transition: all 0.3s ease;
}

.btn-gradient-purple:hover{
    background: linear-gradient(45deg, #A083D1, #5D0E99);
    transform: scale(1.08);
    box-shadow: 0 8px 16px rgba(0,0,0,0.35);
}

/* Login Card Header Gradient */
.card-header-gradient{
    background: linear-gradient(90deg, #9370DB, #4B0082);
    color: white;
     border-top-left-radius: 20px;
      border-top-right-radius: 20px;
    font-weight: bold;
    font-size: 1.25rem;
    text-align: center;
}

/* Login Button inside Card */
.btn-login-purple{
    background: linear-gradient(45deg, #B19CD9, #7A1FC0);
    color: white;
    font-weight: bold;
    font-size: 1.2rem;
    padding: 10px 25px;
    border: none;
    margin-left:45px;
    width:350px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.25);
    transition: all 0.3s ease;
}

.btn-login-purple:hover{
    background: linear-gradient(45deg, #A083D1, #5D0E99);
    transform: scale(1.05);
    box-shadow: 0 6px 12px rgba(0,0,0,0.35);
}

/* Login Card Rounded */
.card{
    border-radius: 25px; /* Rounded corners */
}
.btn-gradient-purple {
  border-radius: 20px !important;
}
/* More rounded card */
.card {
  border-radius: 20px !important;  /* Ensure stronger rounding */
}
.card {
  overflow: hidden;
}

/* More rounded inputs inside the card */
.card .form-control {
  border-radius: 12px;  /* Rounded inputs */
}

/* More rounded login button */
.btn-login-purple {
  border-radius: 20px !important;
}

/* Form Labels */
.form-label{
    font-weight: 500;
}
</style>
</head>

<body class="bg-light">
<!-- Navbar -->
<nav class="navbar bg-gradient-purple">
  <div class="container-fluid navbar-container">
    <a class="navbar-brand text-white" href="#">X-Workz</a>
    <div class="nav-right">
      <a class="btn btn-gradient-purple" href="login">Login</a>
      <a class="btn btn-gradient-purple" href="signup">SignUp</a>
    </div>
  </div>
</nav>

<!-- Login Form -->
<div class="container mt-5">
     <div class="row justify-content-center">
         <div class="col-md-5">
             <div class="card shadow">
                 <div class="card-header card-header-gradient">
                     <h4>Login</h4>
                 </div>
                 <div class="card-body">
                     <form action="userlogincheck" method="post">
                         <!-- EMAIL -->
                         <div class="mb-3">
                             <label class="form-label">Email ID</label>
                             <input type="email" name="emailId" id="email" class="form-control">
                         </div>

                         <!-- PASSWORD -->
                         <div class="mb-3">
                             <label class="form-label">Password</label>
                             <input type="password" name="password" id="password" class="form-control">
                         </div>

                         <div class="d-grid">
                             <button type="submit" class="btn btn-login-purple">Login</button>
                         </div>

                         <!-- Server-side error message -->
                         <div class="mt-2 text-danger" id="errorMessage">
                             ${loginError}
                         </div>
                     </form>
                 </div>
             </div>
         </div>
     </div>
</div>

<script>
// Hide error after 3 seconds
const errorMessage = document.getElementById("errorMessage");
if (errorMessage && errorMessage.innerText.trim() !== "") {
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 3000);
}
</script>

</body>
</html>