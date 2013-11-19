<@c.html title="账本-记账本">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li>
                    <a href="/bill">我的账本</a>
                </li>
                <li class="active">
                    <a href="/bill/add">我要记账</a>
                </li>
            </ul>
        </div>
        <div class="col-xs-9">
            <form id="bill-form" class="form-horizontal" role="form" method="post" action="/bill/add">
                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">日期</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="date" readonly="readonly" id="date" value="${.now?string('yyyy-MM-dd')}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="item" class="col-sm-2 control-label">名目</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="item" id="item" placeholder="请输入名目" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="money" class="col-sm-2 control-label">金额</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="money" id="money" placeholder="请输入金额" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="money" class="col-sm-2 control-label">类型</label>
                    <div class="col-sm-10">
                        <label class="radio-inline">
                            <input type="radio" name="type" value="PAY" checked="checked" /> 支出
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="type" value="INCOME" /> 收入
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remark" class="col-sm-2 control-label">备注</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" name="remark" id="remark" style="resize:none" rows="3"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <button type="button" id="addBill" class="btn btn-primary">提交</button>
                        <button type="submit" class="btn btn-primary">提交并转到账本</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(2)').addClass('active');
        $('#addBill').click(addBill);
    });
    </script>
</@c.html>