<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup Clone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>


<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">

        <!-- Logo -->
        <a class="navbar-brand fw-bold text-primary" href="#">SignUp</a>

        <div class="collapse navbar-collapse" id="navbarNav">

            <!-- Left menu -->
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="#">What We Do</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Create a SignUp</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Find My SignUp</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Help</a></li>
            </ul>

            <!-- Right menu: POST forms for buttons -->
            <ul class="navbar-nav d-flex align-items-center">

                <!-- Log In Form -->
                <li class="nav-item me-3"> <!-- add margin-end here -->
                    <form action="login" method="get" class="d-inline">
                        <button type="submit" class="btn btn-link nav-link text-dark p-0">Log In</button>
                    </form>
                </li>

                <!-- Register Form -->
                <li class="nav-item">
                    <form action="register" method="POST" class="d-inline">
                        <button type="submit" class="btn btn-link nav-link text-dark p-0">Register</button>
                    </form>
                </li>

            </ul>

        </div>
    </div>
</nav>


<div class="container text-center mt-5">
    <form action="showHomePage" method="POST">
    </form>
</div>

</body>
</html>
