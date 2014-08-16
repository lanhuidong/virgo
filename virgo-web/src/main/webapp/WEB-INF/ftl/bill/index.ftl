<@c.html title="我的账本-记账本">
    <div class="row">
        <form role="form" id="query-form" class="form-horizontal" action="/bill" method="post">
            <div class="form-group">
                <div class="col-xs-4 col-sm-2">
                    <input type="text" name="from" class="form-control datepicker" style="cursor:pointer" readonly="readonly" value="${dateRange.from?string('yyyy-MM-dd')}" />
                </div>
                <label class="col-xs-1 col-sm-1 control-label" style="width:10px;margin-left:-12px">到</label>
                <div class="col-xs-4 col-sm-2">
                    <input type="text" name="to" class="form-control datepicker" style="cursor:pointer" readonly="readonly" value="${dateRange.to?string('yyyy-MM-dd')}" />
                </div>
                <div class="col-xs-2 col-sm-1">
                    <button id="query-button" type="button" class="btn btn-primary">查询</button>
                </div>
            </div>
        </form>
        <label class="col-xs-3 col-sm-1">类型</label>
        <label class="radio-inline">
            <input type="radio" name="style" value="table" checked="checked" /> 表格
        </label>
        <label class="radio-inline">
            <input type="radio" name="style" value="chart" /> 图表
        </label>
        <div class="clearfix"></div>
        <div id="table" class="table-responsive">
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
        <div id="chart" style="width:90%;height:500px;display:none"></div>
    </div>
    <script type="text/javascript" src="/js/echarts-plain.js"></script>
    <script type="text/javascript">
    var myChart;
    $(function(){
        $('#chart').css({'width':$('#chart').outerWidth()+'px'});
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(2)').addClass('active');
        $('.datepicker').datepicker();
        queryBill();
        $('#query-button').click(queryBill);
        decideShowType();
        $('input[name="style"]').change(decideShowType);
    });
    </script>
</@c.html>