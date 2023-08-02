const togglePassword = document.querySelector("#togglePassword");
const password = document.querySelector("#password");

togglePassword.addEventListener("click", function () {
    const type = password.getAttribute("type") === "password" ? "text" : "password";
    password.setAttribute("type", type);
    this.classList.toggle("bi-eye");
});



function incProduct(a) {
    window.location.href = "${pageContext.request.contextPath}/client/process?num=inc&id=" + a;
}
function decProduct(a) {
    window.location.href = "${pageContext.request.contextPath}/client/process?num=dec&id=" + a;
}
function delProduct(a) {
    window.location.href = "${pageContext.request.contextPath}/client/delcart?id=" + a;
}



function checkUsernameAndPassword() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    var usernameRegex = /^[a-zA-Z0-9_]{4,20}$/;
    var passwordRegex = /^.{8,}$/;
    if (username === "" || password === "") {
        alert("Please enter both username and password.");
        return false;
    }

//    if (!usernameRegex.test(username)) {
//        alert("Invalid username. Username must be between 4 and 20 characters and can only contain alphanumeric characters and underscores.");
//        return false;
//    }
//
//    if (!passwordRegex.test(password)) {
//        alert("Invalid password. Password must be at least 8 characters long.");
//        return false;
//    }

    document.getElementById("myForm").submit();
}

function validateRegistrationForm() {
    var userName = document.getElementById('userName').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var cfPassword = document.getElementById('cfPassword').value;
    var usernameRegex = /^[a-zA-Z0-9_]{4,20}$/;
    var passwordRegex = /^.{8,}$/;
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (userName === '' || email === '' || password === '' || cfPassword === '') {
        alert('Please fill in all the required fields.');
        return false;
    }
    
    if (!usernameRegex.test(userName)) {
        alert("Invalid username. Username must be between 4 and 20 characters and can only contain alphanumeric characters and underscores.");
        return false;
    }
    
    if(!emailRegex.test(email)){
        alert("Invalid email");
        return false;
    }

    if (!passwordRegex.test(password)) {
        alert("Invalid password. Password must be at least 8 characters long.");
        return false;
    }
    
    if (!passwordRegex.test(cfPassword)) {
        alert("Invalid confirm password. Password must be at least 8 characters long.");
        return false;
    }

    if (password !== cfPassword) {
        alert('Password and Confirm Password do not match.');
        return false;
    }

    document.getElementById("myForm").submit();
}

function validChangePassword() {
    var password = document.getElementById('password').value;
    var cfPassword = document.getElementById('cfPassword').value;
    var passwordRegex = /^.{8,}$/;
    if (password === '' || cfPassword === '') {
        alert('Please fill in all the required fields.');
        return false;
    }

    if (!passwordRegex.test(password)) {
        alert("Invalid password. Password must be at least 8 characters long.");
        return false;
    }
    
    if (!passwordRegex.test(cfPassword)) {
        alert("Invalid confirm password. Password must be at least 8 characters long.");
        return false;
    }

    if (password !== cfPassword) {
        alert('Password and Confirm Password do not match.');
        return false;
    }

    document.getElementById("myForm").submit();
}

function validateForgotPasswordForm() {
    var email = document.getElementById('email').value;
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email === '') {
        alert('Please fill in all the required fields.');
        return false;
    }
    
    if(!emailRegex.test(email)){
        alert("Invalid email");
        return false;
    }

    document.getElementById("myForm").submit();
}

function validateOTP() {
    var otp = document.getElementById('otp').value;
    var otpRegex = /^\d{6}$/;
    if (otp === '') {
        alert('Please fill in all the required fields.');
        return false;
    }
    
    if(!otpRegex.test(otp)){
        alert("Invalid OTP");
        return false;
    }

    document.getElementById("myForm").submit();
}



