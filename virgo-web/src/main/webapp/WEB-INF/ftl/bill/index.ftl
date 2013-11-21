<@c.html title="账本-记账本">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li class="active">
                    <a href="/bill">我的账本</a>
                </li>
                <li>
                    <a href="/bill/add">我要记账</a>
                </li>
            </ul>
        </div>
        <div class="col-xs-9">
            <#list bills as bill>
                ${bill.date}
                <#list bill.items as item>
                    ${item.money}
                </#list>
            </#list>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(2)').addClass('active');
    });
    </script>
</@c.html>