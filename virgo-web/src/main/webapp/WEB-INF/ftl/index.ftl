<@c.html title="首页-记账本">
    <div class="text-center"><h1 class="text-info">版本1.1.0已完成，版本1.1.1开发中……</h1><br/>
    <img src="/img/xxx.jpg" height="500" /></div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(0)').addClass('active');
        $('#sign-btn').click(signup);
        $('#login-form').submit(login);
        $('#regusername').blur(validateUsername);
        $('#regpassword').blur(validatePassword);
    });
    </script>
</@c.html>