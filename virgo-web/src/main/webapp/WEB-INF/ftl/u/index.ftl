<@c.html title="${user.username}的主页-记账本">
    <div class="row">
        <div class="col-xs-3 text-center">
            <img src="/img/xxx.jpg" class="img-rounded" width="100" height="100" />
            <div class="center-block">${user.username}</div>
        </div>
        <div class="col-xs-9">
            <div class="row">
                <label class="col-xs-4 col-sm-2 text-right">用户名</label>
                <label class="col-xs-8 col-sm-4">${user.username}</label>
            </div>
            <div class="row">
                <label class="col-xs-4 col-sm-2 text-right">注册时间</label>
                <label class="col-xs-8 col-sm-4">${user.signTime?string('yyyy-MM-dd')}</label>
            </div>
            <div class="row">
                <label class="col-xs-4 col-sm-2 text-right">上次登录时间</label>
                <label class="col-xs-8 col-sm-4">${user.lastLogin?string('yyyy-MM-dd HH:mm:ss')}</label>
            </div>
            <div class="row">
                <label class="col-xs-4 col-sm-2 text-right">Email</label>
                <label class="col-xs-8 col-sm-4">${user.email?if_exists}</label>
            </div>
            <div class="row">
                <label class="col-xs-4 col-sm-2 text-right">手机</label>
                <label class="col-xs-8 col-sm-4">${user.phone?if_exists}</label>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(1)').addClass('active');
    });
    </script>
</@c.html>