<@c.html title="${user.username}的主页-记账本">
    ${username}的主页
    <script type="text/javascript">
    $(function(){
        $('.nav').find('li').removeClass('active');
        $('.nav').find('li:eq(1)').addClass('active');
    });
    </script>
</@c.html>