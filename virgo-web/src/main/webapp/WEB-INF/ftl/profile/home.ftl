<@c.html title="${user.username}的主页-记账本">
    ${username}的主页
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(1)').addClass('active');
    });
    </script>
</@c.html>