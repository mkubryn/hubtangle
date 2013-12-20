<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, social"/>
    <r:layoutResources/>
</head>

<body class="home">
<header>
    <div class="wrapper cf">

        <layout:include template="logo" />




</header>

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf">

        <div id="welcomeLoginBox">

            <div id="login">

                <h2><span class="fontawesome-lock"></span>Sign In</h2>

                <form action="/hubtangle/j_spring_security_check" method="POST">
                    <fieldset>

                        <p><label for="userLogin">Your login</label></p>
                        <p><input type="userLogin" name="j_username" id="userLogin" value="mail@address.com"
                                  onBlur="if(this.value=='')this.value='mail@address.com'" onFocus="if(this.value=='mail@address.com')this.value=''"></p> <!-- JS because of IE support; better: placeholder="mail@address.com" -->

                        <p><label for="password">Password</label></p>
                        <p><input type="password" name="j_password" id="password" value="password"
                                  onBlur="if(this.value=='')this.value='password'" onFocus="if(this.value=='password')this.value=''"></p> <!-- JS because of IE support; better: placeholder="password" -->

                        <p><input type="submit" value="Sign In"></p>

                    </fieldset>

                </form>

            </div> <!-- end login -->
        </div>

    </div>
    <!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->
</body>
</html>