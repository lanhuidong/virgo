<@c.html title="我的任务列表-记事本">
    <div class="row">
        <div id="todos"></div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(4)').addClass('active');
        queryTodos();
    });
    </script>
</@c.html>