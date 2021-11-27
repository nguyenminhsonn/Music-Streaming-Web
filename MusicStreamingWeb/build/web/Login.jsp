<%-- 
    Document   : Login
    Created on : 21-Sep-2021, 21:03:22
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" asp-append-version="true" />
    
    <title>Login</title>
</head>
<body>
    <div class="big_container">
        <div class="login_container ${requestScope.errorRegister != null?"right-panel-active":""}" id="login_container">
            <div class="form-container sign-up-container">
                <form action="register" method="POST">
                    <h1 class="login_account">Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span style="font-size: 12px ">or use your email for registration</span>
                    <input class="input" type="text" placeholder="Username" name="username"/>
                    <input class="input" type="email" placeholder="Email" name="email"/>
                    <input class="input" type="password" placeholder="Password" name="password"/>
                    <input class="input" type="text" placeholder="Fullname" name="fullname"/>
                    <button id="button" type="submit"> Sign Up</button>
                    <h4 style="color: red">${requestScope.errorRegister}</h4>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="login" method="POST">
                    <h1 class="login_account">Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span style="font-size: 12px">or use your account</span>
                    <input class="input" type="text" placeholder="Username" name="username"/>
                    <input class="input" type="password" placeholder="Password" name="password"/>
                    <a href="#">Forgot your password?</a>
                    <button id="button" type="submit">Sign In</button>
                    <h4 style="color: red">${requestScope.error}</h4>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1 class="login_account">Welcome Back!</h1>
                        <p class="read_text">To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1 class="login_account">Hello, Friend!</h1>
                        <p class="read_text">Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
       const container = document.getElementById('login_container');
       
        signUpButton.addEventListener('click', () => {
            container.classList.add("right-panel-active");
        });
       
        signInButton.addEventListener('click', () => {
            container.classList.remove("right-panel-active");
        });
    </script>
</body>
</html>