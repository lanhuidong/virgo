<@c.html title="我的账本-记账本">
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
            <form role="form" id="query-form" class="form-horizontal" action="/bill" method="post">
                <div class="form-group">
                    <label class="col-xs-1 col-sm-1 control-label">日期</label>
                    <div class="col-xs-5 col-sm-2">
                        <input type="text" name="from" class="form-control datepicker" style="cursor:pointer" readonly="readonly" value="${dateRange.from?string('yyyy-MM-dd')}" />
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label" style="width:10px;margin-left:-12px">到</label>
                    <div class="col-xs-5 col-sm-2">
                        <input type="text" name="to" class="form-control datepicker" style="cursor:pointer" readonly="readonly" value="${dateRange.to?string('yyyy-MM-dd')}" />
                    </div>
                    <div class="col-xs-2 col-sm-2">
                        <button id="query-button" type="button" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </form>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th width="15%">日期</th>
                        <th width="25%" class="text-right">支出</th>
                        <th width="25%" class="text-right">收入</th>
                        <th width="25%" class="text-right">小结</th>
                        <th width="10%" class="text-center">明细</th>
                    </tr>
                    </thead>
                    <tbody><tr><td class="text-center" colspan="5">正在加载数据</td></tr></tbody>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(2)').addClass('active');
        $('.datepicker').datepicker();
        queryBill();
        $('#query-button').click(queryBill);
    });
    </script>
</@c.html>