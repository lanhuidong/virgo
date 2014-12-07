<@c.html title="记账-记账本" init="initBillAdd">
    <div class="row">
        <form id="bill-form" class="form-horizontal" role="form" method="post" action="/bill/add">
            <div class="form-group">
                <label for="date" class="col-xs-3 col-sm-2 control-label text-right">日期</label>
                <div class="col-xs-6 col-sm-2">
                    <input type="text" class="form-control datepicker" style="cursor:pointer" name="date" readonly="readonly" id="date" value="${.now?string('yyyy-MM-dd')}" />
                </div>
            </div>
            <div class="form-group">
                <label for="item" class="col-xs-3 col-sm-2 control-label text-right">名目</label>
                <div class="col-xs-6 col-sm-4">
                    <input type="text" class="form-control" name="item" id="item" placeholder="请输入名目" />
                </div>
            </div>
            <div class="form-group">
                <label for="money" class="col-xs-3 col-sm-2 control-label text-right">金额</label>
                <div class="col-xs-6 col-sm-2">
                    <input type="text" class="form-control" name="money" id="money" placeholder="请输入金额" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-sm-2 control-label text-right">类型</label>
                <div class="col-xs-9 col-sm-10">
                    <label class="radio-inline">
                        <input type="radio" name="type" value="PAY" checked="checked" /> 支出
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="type" value="INCOME" /> 收入
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-xs-3 col-sm-2 control-label text-right">备注</label>
                <div class="col-xs-8 col-sm-8">
                    <textarea class="form-control" name="remark" id="remark" style="resize:none" rows="3" placeholder="可不填，不能超过255个字符"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-sm-2 control-label"></label>
                <div class="col-xs-9 col-sm-10">
                    <button type="button" id="addBill" class="btn btn-primary">提交</button>
                    <label id="add-tip" class="text-danger virgo-input-error"></label>
                </div>
            </div>
        </form>
    </div>
</@c.html>