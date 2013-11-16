<@c.html title="首页">
    欢迎使用FreeMarker! ${.now}
    <form action="/login" method="post">
        用户名：<input name="j_username" type="text" /><br/>
        密  码：<input name="j_password" type="text" /><br/>
        <input type="submit" value="登录" /><br/>
    </form>
</@c.html>