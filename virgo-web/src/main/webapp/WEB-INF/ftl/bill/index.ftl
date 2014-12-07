<@c.html title="我的账本-记账本" init="initBillIndex">
    <div class="row">
        <form role="form" id="query-form" class="form-horizontal" action="/bill" method="post">
            <div class="form-group">
                    <select id="year" name="year" class="form-control input-sm pull-left" style="width:89px;margin-left:15px">
                        <#list years as y>
                            <option value="${y}" <#if y=year>selected="selected"</#if>>${y}年</option>
                        </#list>
                    </select>
                    <select id="month" name="month" class="form-control input-sm pull-left" style="width:72px;margin-left:5px">
                        <#list 1..12 as m>
                            <option value="${m}" <#if m=month>selected="selected"</#if>>${m}月</option>
                        </#list>
                    </select>
            </div>
        </form>
        <label>类型：</label>
        <label class="radio-inline">
            <input type="radio" name="style" value="table" checked="checked" /> 表格
        </label>
        <label class="radio-inline">
            <input type="radio" name="style" value="chart" /> 折线图
        </label>
        <label class="radio-inline">
            <input type="radio" name="style" value="pie" /> 饼图
        </label>
        <div class="clearfix"></div>
        <div id="table" class="table-responsive">
            <table id="bill-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th width="15%">日期</th>
                    <th width="25%" class="text-right">支出</th>
                    <th width="25%" class="text-right">收入</th>
                    <th width="25%" class="text-right">小结</th>
                    <th width="10%" class="text-center">明细</th>
                </tr>
                </thead>
                <tbody id="bill-tbody"><tr><td class="text-center" colspan="5">正在加载数据</td></tr></tbody>
            </table>
        </div>
        <div id="chart" style="height:500px;display:none"></div>
        <div id="pie" style="height:500px;display:none">
            <div id="incomePie" class="col-xs-6" style="height:500px"></div>
            <div id="payPie" class="col-xs-6" style="height:500px"></div>
        </div>
    </div>
    <script type="text/javascript" src="/js/echarts-plain.js"></script>
    <script type="text/javascript">
    var myChart;
    var incomePieChart;
    var payPieChart;
    </script>
</@c.html>