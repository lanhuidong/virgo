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
                    <label class="col-sm-1 control-label">日期</label>
                    <div class="col-sm-2">
                        <input type="text" name="from" class="form-control datepicker" value="${dateRange.from?string('yyyy-MM-dd')}" />
                    </div>
                    <label class="col-sm-1 control-label" style="width:10px;margin-left:-12px">到</label>
                    <div class="col-sm-2">
                        <input type="text" name="to" class="form-control datepicker" value="${dateRange.to?string('yyyy-MM-dd')}" />
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </form>
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
                <tbody>
                <#assign totalPay=0 totalIncome=0>
                <#list bills as bill>
                    <#assign pay=0 income=0 />
                    <#list bill.items as item>
                        <#if item.type=='PAY'>
                            <#assign pay=pay+item.money>
                        <#else>
                            <#assign income=income+item.money>
                        </#if>
                    </#list>
                    <tr>
                        <td>${bill.date?string('yyyy-MM-dd')}</td>
                        <td class="text-right text-danger">${pay?string('#0.00')}</td>
                        <td class="text-right text-success">${income?string('#0.00')}</td>
                        <td <#if income-pay lt 0>class="text-danger text-right"<#else>class="text-right text-success"</#if>>${(income-pay)?string('#0.00')}</td>
                        <td class="text-center text-info view-item" style="cursor:pointer">查看</td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="5" style="padding:0;border:none">
                            <table class="table" style="margin:0;background:#FCF8E3">
                                <tbody>
                                    <#list bill.items as item>
                                    <tr>
                                        <td width="15%">${item.item}</td>
                                        <td width="25%" <#if item.type=='PAY'>class="text-right text-danger"<#else>class="text-right text-success"</#if>>${item.money?string('#0.00')}</td>
                                        <td width="50%" class="text-center" colspan="2">${item.remark?if_exists}</td>
                                        <td width="10%" class="text-center text-info"><label id="${item.id}" class="del-item" style="cursor:pointer">删除</label></td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <#assign totalPay=totalPay+pay totalIncome=totalIncome+income />
                </#list>
            </tbody>
            <tfoot>
                <tr>
                    <th>结算</th>
                    <th class="text-right text-danger">${totalPay?string('#0.00')}</th>
                    <th class="text-right text-success">${totalIncome?string('#0.00')}</th>
                    <th <#if totalIncome-totalPay lt 0>class="text-danger text-right"<#else>class="text-right text-success"</#if>>${(totalIncome-totalPay)?string('#0.00')}</th>
                    <th></th>
                </tr>
            </tfoot>
            </table>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(2)').addClass('active');
        $('.datepicker').datepicker();
        $('.view-item').click(toggleBillItem);
        $('.del-item').click(deleteBillItem);
    });
    </script>
</@c.html>