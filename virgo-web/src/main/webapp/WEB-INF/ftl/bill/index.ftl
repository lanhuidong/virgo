<@c.html title="账本-记账本">
    ${user.username}的账本
    <script type="text/javascript">
    $(function(){
        $('.nav').find('li').removeClass('active');
        $('.nav').find('li:eq(2)').addClass('active');
    });
    </script>
</@c.html>