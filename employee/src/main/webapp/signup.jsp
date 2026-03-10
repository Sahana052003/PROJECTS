<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Registration</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
/* Navbar */
.navbar-container{
    height:100px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    width:100%;
    padding-left:0;
    padding-right:0;
}

.navbar-brand{
    margin-left:25px;
    font-weight:bold;
    font-size:2.5rem;
    letter-spacing:2px;
    text-transform:uppercase;
    text-shadow:1px 1px 3px rgba(0,0,0,0.3);
}

.nav-right{
    display:flex;
    margin-right:25px;
    gap:20px;
}

.bg-gradient-purple{
    background: linear-gradient(90deg, #9370DB, #4B0082);
}

/* Make the whole card rounded */
.card {
  border-radius: 20px !important;
  overflow: hidden; /* to prevent header bg from spilling outside rounded corners */
}

/* Round only top corners of the header to match the card */
.card-header-gradient {
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
}

/* Round inputs and selects inside the card */
.card .form-control,
.card .form-select {
  border-radius: 12px;
}


/* Navbar buttons */
.btn-gradient-purple{
    background: linear-gradient(45deg, #B19CD9, #7A1FC0);
    color: white;
    border: none;
    font-weight: bold;
    font-size: 1.3rem;
    padding: 12px 28px;
    border-radius: 8px;
    box-shadow: 0 6px 12px rgba(0,0,0,0.25);
    transition: all 0.3s ease;
}

.btn-gradient-purple:hover{
    background: linear-gradient(45deg, #A083D1, #5D0E99);
    transform: scale(1.08);
    box-shadow: 0 8px 16px rgba(0,0,0,0.35);
}

/* Card Header Gradient */
.card-header-gradient{
    background: linear-gradient(90deg, #9370DB, #4B0082);
    color: white;
    font-weight: bold;
    height:70px;
    font-size: 1.3rem;
    text-align: center;
}

/* Register Button */
.btn-register-purple{
    background: linear-gradient(45deg, #B19CD9, #7A1FC0);
    color: white;
    font-weight: bold;
    font-size: 1.2rem;
    padding: 12px 25px;
    width:350px;
    margin-left:100px;
    border: none;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.25);
    transition: all 0.3s ease;
}

.btn-register-purple:hover{
    background: linear-gradient(45deg, #A083D1, #5D0E99);
    transform: scale(1.05);
    box-shadow: 0 6px 12px rgba(0,0,0,0.35);
}

/* Form Inputs Focus */
.form-control:focus, .form-select:focus{
    border-color: #7A1FC0;
    box-shadow: 0 0 8px rgba(122,31,192,0.3);
}

/* Form Labels */
.form-label{
    font-weight:500;
}

 h4 {
    font-size: calc(1.275rem + .3vw);
    margin-top:12px;
}

/* Remove Bootstrap's green checkmark icon for valid inputs */
input.is-valid {
    background-image: none !important;
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

<!-- Employee Registration Form -->
<div class="container mt-5">
<div class="row justify-content-center">
<div class="col-md-6">

<div class="card shadow">

<div class="card-header card-header-gradient">
<h4>Employee Registration</h4>
</div>

<div class="card-body">
<form action="userlogin" method="post" onsubmit="return validateForm()">

<!-- NAME -->
<div class="mb-3">
<label class="form-label">User Name</label>
<input type="text" class="form-control" id="userName" name="userName">
<div class="invalid-feedback" id="nameError"></div>
</div>

<!-- EMAIL -->
<div class="mb-3">
<label class="form-label">Email ID</label>
<input type="email"
       class="form-control ${error != null ? 'is-invalid' : ''}"
       id="email" name="emailId"
       value="${employeeDTO != null ? employeeDTO.emailId : ''}">
<div class="invalid-feedback" id="emailError">
    <c:if test="${error != null}">
        ${error}
    </c:if>
</div>
</div>

<!-- AGE -->
<div class="mb-3">
<label class="form-label">Age</label>
<input type="number" class="form-control" id="age" name="age">
<div class="invalid-feedback" id="ageError"></div>
</div>

<!-- ADDRESS -->
<div class="mb-3">
<label class="form-label">Address</label>
<textarea class="form-control" id="address" name="address"></textarea>
<div class="invalid-feedback" id="addressError"></div>
</div>

<!-- MOBILE -->
<div class="mb-3">
<label class="form-label">Mobile Number</label>
<input type="text" class="form-control" id="mobileNumber" name="mobileNumber">
<div class="invalid-feedback" id="mobileError"></div>
</div>

<!-- GENDER -->
<div class="mb-3">
<label class="form-label">Gender</label>
<select class="form-select" id="gender" name="gender">
<option value="">Select Gender</option>
<option>Male</option>
<option>Female</option>
<option>Other</option>
</select>
<div class="invalid-feedback" id="genderError"></div>
</div>

<!-- PASSWORD -->
<div class="mb-3">
<label class="form-label">Password</label>
<input type="password" class="form-control" id="password" name="password">
<div class="invalid-feedback" id="passwordError"></div>
</div>

<!-- CONFIRM PASSWORD -->
<div class="mb-3">
<label class="form-label">Confirm Password</label>
<input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
<div class="invalid-feedback" id="confirmError"></div>
</div>

<div class="d-grid">
<button type="submit" class="btn btn-register-purple">Register</button>
</div>

</form>
</div>
</div>

</div>
</div>
</div>


<script>

function validateForm(){

let isValid = true;

let name = document.getElementById("userName");
let email = document.getElementById("email");
let age = document.getElementById("age");
let address = document.getElementById("address");
let mobile = document.getElementById("mobileNumber");
let gender = document.getElementById("gender");
let password = document.getElementById("password");
let confirmPassword = document.getElementById("confirmPassword");

clearErrors();

let namePattern=/^[A-Za-z ]+$/;
if(!namePattern.test(name.value)){
showError(name,"nameError","Name should contain only letters");
isValid=false;
}

let emailPattern=/^[a-zA-Z0-9._%+-]+@gmail\.com$/;
if(!emailPattern.test(email.value)){
showError(email,"emailError","Email must end with @gmail.com");
isValid=false;
}

if(age.value==="" || age.value<=18){
showError(age,"ageError","Age must be greater than 18");
isValid=false;
}

if(address.value.trim()===""){
showError(address,"addressError","Address cannot be empty");
isValid=false;
}

let mobilePattern=/^[6-9][0-9]{9}$/;
if(!mobilePattern.test(mobile.value)){
showError(mobile,"mobileError","Mobile must start with 6-9 and be 10 digits");
isValid=false;
}

if(gender.value===""){
showError(gender,"genderError","Please select gender");
isValid=false;
}

let passwordPattern=/^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;
if(!passwordPattern.test(password.value)){
showError(password,"passwordError","Password must contain uppercase, number, special character");
isValid=false;
}

if(password.value!==confirmPassword.value){
showError(confirmPassword,"confirmError","Passwords do not match");
isValid=false;
}

return isValid;

}


function showError(input,errorId,message){

input.classList.add("is-invalid");
input.classList.remove("is-valid");

document.getElementById(errorId).innerText=message;

}


function showSuccess(input,errorId){

input.classList.remove("is-invalid");
input.classList.remove("is-valid");

document.getElementById(errorId).innerText="";

}


function clearErrors(){

document.querySelectorAll(".form-control, .form-select").forEach(e=>{
e.classList.remove("is-invalid","is-valid");
});

document.querySelectorAll(".invalid-feedback").forEach(e=>{
e.innerText="";
});

}


/* LIVE VALIDATION */

document.getElementById("userName").addEventListener("keyup",function(){

let pattern=/^[A-Za-z ]+$/;

if(!pattern.test(this.value)){
showError(this,"nameError","Name should contain only letters");
}else{
showSuccess(this,"nameError");
}

});


document.getElementById("email").addEventListener("keyup",function(){

let pattern=/^[a-zA-Z0-9._%+-]+@gmail\.com$/;

if(!pattern.test(this.value)){
showError(this,"emailError","Email must end with @gmail.com");
}else{
showSuccess(this,"emailError");
}

});


document.getElementById("age").addEventListener("keyup",function(){

if(this.value==="" || this.value<=18){
showError(this,"ageError","Age must be greater than 18");
}else{
showSuccess(this,"ageError");
}

});


document.getElementById("address").addEventListener("keyup",function(){

if(this.value.trim()===""){
showError(this,"addressError","Address cannot be empty");
}else{
showSuccess(this,"addressError");
}

});


document.getElementById("mobileNumber").addEventListener("keyup",function(){

let pattern=/^[6-9][0-9]{9}$/;

if(!pattern.test(this.value)){
showError(this,"mobileError","Mobile must start with 6-9 and be 10 digits");
}else{
showSuccess(this,"mobileError");
}

});


document.getElementById("gender").addEventListener("change",function(){

if(this.value===""){
showError(this,"genderError","Please select gender");
}else{
showSuccess(this,"genderError");
}

});


document.getElementById("password").addEventListener("keyup",function(){

let pattern=/^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;

if(!pattern.test(this.value)){
showError(this,"passwordError","Password must contain uppercase, number, special character");
}else{
showSuccess(this,"passwordError");
}

});


document.getElementById("confirmPassword").addEventListener("keyup",function(){

let password=document.getElementById("password").value;

if(this.value!==password){
showError(this,"confirmError","Passwords do not match");
}else{
showSuccess(this,"confirmError");
}

});


window.onload = function() {
    const email = document.getElementById("email");
    const emailErrorDiv = document.getElementById("emailError");

    email.addEventListener("keyup", function() {
        let pattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;

        // Only clear the server error if user changes the field
        let serverError = emailErrorDiv.innerText.includes("exists");

        if (!pattern.test(this.value)) {
            this.classList.add("is-invalid");
            this.classList.remove("is-valid");
            emailErrorDiv.innerText = "Email must end with @gmail.com";
        } else {
            if (!serverError) { // don't override server error
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
                emailErrorDiv.innerText = "";
            }
        }
    });
};
</script>
</body>
</html>