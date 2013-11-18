<@c.html title="首页-记账本">
    This is main content.
    <script type="text/javascript">
    $(function(){
        $('.nav').find('li').removeClass('active');
        $('.nav:eq(0)').find('li:eq(0)').addClass('active');
        $('#sign-btn').click(signup);
        $('#login-form').submit(login);
        $('#regusername').blur(validateUsername);
        $('#regpassword').blur(validatePassword);
    });
    </script>
</@c.html>