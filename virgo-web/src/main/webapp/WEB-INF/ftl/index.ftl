<@c.html title="首页-记账本">
    <div class="text-center">Android APP download:
        <a target="_blank" href="https://play.google.com/store/apps/details?id=com.nexusy.virgo.android">
            <img src="/img/bill-icon.png" style="width:48px;height:48px;border:none" />
            <br/>
            <img src="/img/bill-qrcode.png" style="border:none"/>
        </a>
    </div>
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