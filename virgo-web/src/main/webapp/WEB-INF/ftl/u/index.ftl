<@c.html title="${user.username}的主页-记账本">
    <div class="row">
        <div class="col-xs-3 text-center">
            <img src="/img/xxx.jpg" class="img-rounded" width="100" height="100" />
            <div class="center-block">${user.username}</div>
        </div>
        <div class="col-xs-9">
            <form role="form" id="edit-form">
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
                    <span class="col-xs-8 col-sm-4">${user.email?if_exists}</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input type="text" class="form-control input-sm">
                    </div>
                </div>
                <div class="row" style="margin:5px 0;height:30px;line-height:30px">
                    <span class="col-xs-4 col-sm-2 text-right text-muted">手机</span>
                    <span class="col-xs-8 col-sm-4">${user.phone?if_exists}</span>
                    <div class="col-xs-8 col-sm-4 form-group" style="display:none;margin:0">
                        <input type="text" class="form-control input-sm">
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
    });
    </script>
</@c.html>