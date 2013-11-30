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
    <#if bills?size gt 0>
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
        <tr id="${bill.id}">
            <td>${bill.date?string('yyyy-MM-dd')}</td>
            <td class="text-right text-danger pay">${pay?string('#0.00')}</td>
            <td class="text-right text-success income">${income?string('#0.00')}</td>
            <#if income-pay lt 0>
            <td class="text-danger text-right money">${(pay-income)?string('#0.00')}</td>
            <#else>
            <td class="text-right text-success money">${(income-pay)?string('#0.00')}</td>
            </#if>
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
                            <td width="10%" class="text-center text-info"><label id="${item.id}" bid="${bill.id}" class="del-item" style="cursor:pointer">删除</label></td>
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
            <th id="total-pay" class="text-right text-danger">${totalPay?string('#0.00')}</th>
            <th id="total-income" class="text-right text-success">${totalIncome?string('#0.00')}</th>
            <#if totalIncome-totalPay lt 0>
            <th id="total-money" class="text-danger text-right">${(totalPay-totalIncome)?string('#0.00')}</th>
            <#else>
            <th id="total-money" class="text-right text-success">${(totalIncome-totalPay)?string('#0.00')}</th>
            </#if>
            <th></th>
        </tr>
    </tfoot>
    <#else>
        <tbody><tr><td class="text-center" colspan="5">暂无数据</td></tr></tbody>
    </#if>
</table>
</div>