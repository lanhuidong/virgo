<@c.html title="${user.username}的主页-记账本">
    <div class="row">
        <div class="col-xs-3 text-center">
            <img src="/img/xxx.jpg" class="img-rounded" width="100" height="100" />
            <div class="center-block">${user.username}</div>
        </div>
        <div class="col-xs-9">
            <form role="form" id="basic-form">
                <div class="row">
                    <label class="col-xs-4 col-sm-2 text-right">基本信息</label>
                    <label class="col-xs-8 col-sm-4" style="background:#BBB;margin:10px 0 0"></label>
                    <button id="edit" type="button" class="btn btn-default btn-xs">编辑</button>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">用户名</span>
                    <span class="col-xs-8 col-sm-4">${user.username}</span>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">注册时间</span>
                    <span class="col-xs-8 col-sm-4">${user.signTime?string('yyyy-MM-dd')}</span>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">上次登录时间</span>
                    <span class="col-xs-8 col-sm-4">${user.lastLogin?string('yyyy-MM-dd HH:mm:ss')}</span>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">Email</span>
                    <span class="col-xs-8 col-sm-4">${u.email?if_exists}</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input name="email" type="text" class="form-control input-sm">
                    </div>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">手机</span>
                    <span class="col-xs-8 col-sm-4">${u.phone?if_exists}</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input name="phone" type="text" class="form-control input-sm">
                    </div>
                </div>
            </form>
            <form role="form" id="password-form">
                <div class="row">
                    <label class="col-xs-4 col-sm-2 text-right">修改密码</label>
                    <label class="col-xs-8 col-sm-4" style="background:#BBB;margin:10px 0 0"></label>
                    <button id="modify" type="button" class="btn btn-default btn-xs">修改</button>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">旧密码</span>
                    <span class="col-xs-8 col-sm-4">********</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input name="oldPassword" type="password" class="form-control input-sm" placeholder="6-20个字符">
                    </div>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">新密码</span>
                    <span class="col-xs-8 col-sm-4">********</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input name="newPassword" type="password" class="form-control input-sm" placeholder="6-20个字符">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(1)').addClass('active');
        $('#edit').click(editProfile);
        $('#modify').click(modifyPassword);
    });
    </script>
</@c.html>