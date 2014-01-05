<div id="welcomeLoginBox">

    <div id="login">

        <h2><span class="fontawesome-lock"></span>Please login or register</h2>

        <form action="${request.contextPath}/j_spring_security_check" method="POST">
            <fieldset>

                <p><label for="userLogin">Your login</label></p>

                <p><input type="userLogin" name="j_username" id="userLogin" value="user1"
                          onBlur="if (this.value == '')this.value = 'username'"
                          onFocus="if (this.value == 'username')this.value = ''">
                </p> <!-- JS because of IE support; better: placeholder="mail@address.com" -->

                <p><label for="password">Password</label></p>

                <p><input type="password" name="j_password" id="password" value="password"
                          onBlur="if (this.value == '')this.value = 'password'"
                          onFocus="if (this.value == 'password')this.value = ''">
                </p> <!-- JS because of IE support; better: placeholder="password" -->

                <p><input type="submit" value="Login"></p>
                <p><input type="submit" value="Register"></p>

            </fieldset>

        </form>

    </div>
</div>